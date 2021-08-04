package br.com.zupacademy.gabrielamartins.ecommerce.controller;

import br.com.zupacademy.gabrielamartins.ecommerce.config.UploaderFake;
import br.com.zupacademy.gabrielamartins.ecommerce.config.security.AutenticacaoService;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Produto;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Usuario;
import br.com.zupacademy.gabrielamartins.ecommerce.repository.CategoriaRepository;
import br.com.zupacademy.gabrielamartins.ecommerce.repository.ProdutoRepository;
import br.com.zupacademy.gabrielamartins.ecommerce.requestDto.ImagemProdutoRequest;
import br.com.zupacademy.gabrielamartins.ecommerce.requestDto.ProdutoRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
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

    @PersistenceContext
    EntityManager manager;

    @Autowired
    AutenticacaoService autenticacaoService;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrarProduto(@Valid @RequestBody ProdutoRequestDto produtoRequestDto, @AuthenticationPrincipal Usuario usuario){
        Optional<Produto> produtoObject = produtoRequestDto.converteParaProduto(categoriaRepository, usuario);
        if(produtoObject.isPresent()){
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



       Optional<Produto> produtoObj = produtoRepository.findById(id);
        if(produtoObj.isPresent()) {
            Produto produto = produtoObj.get();
            if(!produto.pertenceAoUsuario(usuario.getEmail())) return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Você não pode adicionar fotos a um produto que não é seu");

            Set<String> links = uploaderFake.envia(request.getImagens());
            produto.associaImagens(links);
            produtoRepository.save(produto);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();


    }


}
