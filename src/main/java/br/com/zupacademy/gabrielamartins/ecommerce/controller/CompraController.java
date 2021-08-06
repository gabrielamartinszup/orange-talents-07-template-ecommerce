package br.com.zupacademy.gabrielamartins.ecommerce.controller;


import br.com.zupacademy.gabrielamartins.ecommerce.config.Emails;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Compra;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Usuario;
import br.com.zupacademy.gabrielamartins.ecommerce.repository.CompraRepository;
import br.com.zupacademy.gabrielamartins.ecommerce.repository.ProdutoRepository;
import br.com.zupacademy.gabrielamartins.ecommerce.requestDto.CompraRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private Emails emails;

    @PostMapping
    @Transactional
    public ResponseEntity<?> realizarCompra(@RequestBody @Valid CompraRequestDto requestDto, @AuthenticationPrincipal Usuario usuario){

        Optional<Compra> compraObject = requestDto.converteParaCompra(produtoRepository, usuario);

        if (compraObject.isPresent()){
            Compra compra = compraObject.get();
            compraRepository.save(compra);
            emails.novaCompra(compra);


            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(compra.getUrlPagamento())).build();
        }

        return ResponseEntity.badRequest().body("Erro ao concluir a compra");
    }


}
