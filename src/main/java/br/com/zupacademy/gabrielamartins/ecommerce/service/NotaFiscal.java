package br.com.zupacademy.gabrielamartins.ecommerce.service;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Compra;
import br.com.zupacademy.gabrielamartins.ecommerce.component.EventoCompraSucesso;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal implements EventoCompraSucesso {

    @Override
    public void processa(Compra compra){
        Assert.isTrue(compra.processadaComSucesso(), "Compra nào concluida com sucesso: " + compra);
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getUsuario().getId());

            restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);


        }

}
