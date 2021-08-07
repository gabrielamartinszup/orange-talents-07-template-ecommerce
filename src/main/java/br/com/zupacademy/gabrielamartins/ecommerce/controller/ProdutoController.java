package br.com.zupacademy.gabrielamartins.ecommerce.controller;

import br.com.zupacademy.gabrielamartins.ecommerce.component.UploaderFake;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Opiniao;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Produto;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Usuario;
import br.com.zupacademy.gabrielamartins.ecommerce.repository.CategoriaRepository;
import br.com.zupacademy.gabrielamartins.ecommerce.repository.OpiniaoRepository;
import br.com.zupacademy.gabrielamartins.ecommerce.repository.ProdutoRepository;
import br.com.zupacademy.gabrielamartins.ecommerce.requestDto.ImagemProdutoRequest;
import br.com.zupacademy.gabrielamartins.ecommerce.requestDto.OpiniaoRequestDto;
import br.com.zupacademy.gabrielamartins.ecommerce.requestDto.ProdutoRequestDto;
import br.com.zupacademy.gabrielamartins.ecommerce.responseDto.DetalheProdutoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UploaderFake uploaderFake;

    @Autowired
    private OpiniaoRepository opiniaoRepository;



    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrarProduto(@Valid @RequestBody ProdutoRequestDto produtoRequestDto, @AuthenticationPrincipal Usuario usuario) {
        Optional<Produto> produtoObject = produtoRequestDto.converteParaProduto(categoriaRepository, usuario);
        if (produtoObject.isPresent()) {
            produtoRepository.save(produtoObject.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = "/{id}/imagens")
    @Transactional
    public ResponseEntity<?> adicionarImagens(@PathVariable Long id, @Valid ImagemProdutoRequest request, @AuthenticationPrincipal Usuario usuario) {

        /*
        1) enviar imagens para o local onde elas vão ficar
        2) pegar os links de todas as imagens
        3) associar esses links com o produto em questão
        4) preciso carregar o produto
        5) depois que associar eu preciso atualizar a nova versão do produto
         */


        Optional<Produto> produtoObject = produtoRepository.findById(id);
        if (produtoObject.isPresent()) {
            Produto produto = produtoObject.get();
            if (!produto.pertenceAoUsuario(usuario.getEmail())) return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Você não pode adicionar fotos a um produto que não é seu");

            Set<String> links = uploaderFake.envia(request.getImagens());
            produto.associaImagens(links);
            produtoRepository.save(produto);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();


    }

    @PostMapping("/{id}/opinioes")
    @Transactional
    public ResponseEntity<?> cadastrarOpiniao(@PathVariable Long id, @Valid @RequestBody OpiniaoRequestDto request, @AuthenticationPrincipal Usuario usuario) {

        Optional<Produto> produtoObject = produtoRepository.findById(id);

        if (produtoObject.isPresent()) {
            Produto produto = produtoObject.get();

            Opiniao opiniao = request.converteParaOpiniao(produto, usuario);
            opiniaoRepository.save(opiniao);
            return ResponseEntity.ok().build();

        }
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheProdutoResponse> detalharProduto(@PathVariable Long id){

        Optional<Produto> produtoObject = produtoRepository.findById(id);
        if(produtoObject.isPresent()){

            Produto produto = produtoObject.get();
            return ResponseEntity.ok(new DetalheProdutoResponse(produto));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }




}
