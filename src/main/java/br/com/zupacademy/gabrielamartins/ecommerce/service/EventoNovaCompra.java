package br.com.zupacademy.gabrielamartins.ecommerce.service;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Compra;
import br.com.zupacademy.gabrielamartins.ecommerce.component.EventoCompraSucesso;
import br.com.zupacademy.gabrielamartins.ecommerce.model.enums.StatusTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventoNovaCompra {

    @Autowired
    private Set<EventoCompraSucesso> eventosCompraSucesso;

    @Autowired
    private Emails emails;

    public StatusTransacao processa(Compra compra){

        if(compra.processadaComSucesso()){

            eventosCompraSucesso.forEach(evento -> evento.processa(compra));
            emails.confirmacaoCompra(compra);
            return StatusTransacao.sucesso;
        } else{

            emails.compraRejeitada(compra);
            return StatusTransacao.erro;
        }
    }

}
