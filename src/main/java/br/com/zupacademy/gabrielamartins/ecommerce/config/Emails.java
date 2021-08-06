package br.com.zupacademy.gabrielamartins.ecommerce.config;

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
}
