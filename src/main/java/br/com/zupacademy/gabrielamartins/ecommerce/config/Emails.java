package br.com.zupacademy.gabrielamartins.ecommerce.config;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Pergunta;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Produto;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Emails {

    @Autowired
    private Mailer mailer;


    public void novaPergunta(Pergunta pergunta) {

        mailer.enviar("<html>...</html>", "Nova pergunta sobre o produto...", "novapergunta@nossoecommerce.com",
                pergunta.getUsuario().getEmail(), pergunta.getDonoProduto().getEmail());
    }
}
