package br.com.zupacademy.gabrielamartins.ecommerce.service;

import br.com.zupacademy.gabrielamartins.ecommerce.component.Mailer;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Compra;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Emails {

    @Autowired
    private Mailer mailer;


    public void novaPergunta(Pergunta pergunta) {

        mailer.enviar("<html>...</html>", "Nova pergunta sobre o produto...", "novapergunta@nossoecommerce.com",
                pergunta.getUsuario().getEmail(), pergunta.getDonoProduto().getEmail());
    }

    public void novaCompra(Compra compra){

        mailer.enviar("<html>...</html>", "Alguém quer comprar o seu produto..", "novacompra@nossoecommerce.com",
                compra.getUsuario().getEmail(), compra.getProduto().getUsuario().getEmail());

    }

    public void confirmacaoCompra(Compra compra) {

        mailer.enviar("<html>...</html>", "Compra efetuada com sucesso!!",  "confirmacaocompra@nossoecommerce.com",
                compra.getUsuario().getEmail(), compra.toString());

    }

    public void compraRejeitada(Compra compra) {

        mailer.enviar("<html>...</html>", "A sua compra com o id " + compra.getId() + " foi recusada. Você pode tentar comprar novamente em: localhost:8080/compras ",  "compras@nossoecommerce.com",
                "compras@nossoecommerce.com", compra.getUsuario().getEmail());
    }
}
