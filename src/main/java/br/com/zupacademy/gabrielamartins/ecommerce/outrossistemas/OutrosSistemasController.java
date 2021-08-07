package br.com.zupacademy.gabrielamartins.ecommerce.outrossistemas;

import br.com.zupacademy.gabrielamartins.ecommerce.outrossistemas.CompraNFRequest;
import br.com.zupacademy.gabrielamartins.ecommerce.outrossistemas.CompraRankingRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutrosSistemasController {

    @PostMapping(value = "/notas-fiscais")
    public void criaNotaFiscal(@Valid @RequestBody CompraNFRequest request) throws InterruptedException {
        System.out.println("criando nota fiscal para a compra com o id " + request.getIdCompra() + " do comprador com o id " + request.getIdComprador());
        Thread.sleep(150);
    }


    @PostMapping(value = "/ranking")
    public void ranking(@Valid @RequestBody CompraRankingRequest request) throws InterruptedException {
        System.out.println("criando ranking para a compra com o id " + request.getIdCompra() + " do vendedor com o id " + request.getIdVendedor());
        Thread.sleep(150);
    }



}
