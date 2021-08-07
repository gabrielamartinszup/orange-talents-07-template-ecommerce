package br.com.zupacademy.gabrielamartins.ecommerce.controller;


import br.com.zupacademy.gabrielamartins.ecommerce.component.RetornoGatewayPagamento;
import br.com.zupacademy.gabrielamartins.ecommerce.model.enums.StatusTransacao;
import br.com.zupacademy.gabrielamartins.ecommerce.service.Emails;
import br.com.zupacademy.gabrielamartins.ecommerce.model.*;
import br.com.zupacademy.gabrielamartins.ecommerce.repository.CompraRepository;
import br.com.zupacademy.gabrielamartins.ecommerce.repository.ProdutoRepository;
import br.com.zupacademy.gabrielamartins.ecommerce.requestDto.CompraRequestDto;
import br.com.zupacademy.gabrielamartins.ecommerce.requestDto.PagSeguroRequestDto;
import br.com.zupacademy.gabrielamartins.ecommerce.requestDto.PaypalRequestDto;
import br.com.zupacademy.gabrielamartins.ecommerce.responseDto.TransacaoResponse;
import br.com.zupacademy.gabrielamartins.ecommerce.service.EventoNovaCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


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
    @Autowired
    private EventoNovaCompra eventosNovaCompra;

    @PostMapping
    @Transactional
    public ResponseEntity<?> realizarCompra(@RequestBody @Valid CompraRequestDto requestDto, @AuthenticationPrincipal Usuario usuario) {

        Optional<Compra> compraObject = requestDto.converteParaCompra(produtoRepository, usuario);

        if (compraObject.isPresent()) {
            Compra compra = compraObject.get();
            compraRepository.save(compra);
            emails.novaCompra(compra);


            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(compra.getUrlPagamento())).build();
        }

        return ResponseEntity.badRequest().body("Erro ao concluir a compra");
    }

    @PostMapping(value = "/retorno-pagseguro/{id}")
    @Transactional
    public ResponseEntity<?> processarPagamentoPagSeguro(@PathVariable Long id, @RequestBody PagSeguroRequestDto pagSeguroRequestDto) {

        return processa(id, pagSeguroRequestDto);

    }

    @PostMapping(value = "/retorno-paypal/{id}")
    @Transactional
    public ResponseEntity<?> processarPagamentoPaypal(@PathVariable Long id, @RequestBody PaypalRequestDto paypalRequestDto) {


        return processa(id, paypalRequestDto);


    }

    private ResponseEntity<TransacaoResponse> processa(Long id, RetornoGatewayPagamento retornoGatewayPagamento) {

        Optional<Compra> compraObject = compraRepository.findById(id);
        if (compraObject.isPresent()) {

            Compra compra = compraObject.get();
            compra.adicionaTransacao(retornoGatewayPagamento);
            compraRepository.save(compra);
            StatusTransacao status = eventosNovaCompra.processa(compra);

            return ResponseEntity.ok().body(new TransacaoResponse(status));


        }

        return ResponseEntity.badRequest().build();
    }


}
