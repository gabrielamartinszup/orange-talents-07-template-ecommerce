package br.com.zupacademy.gabrielamartins.ecommerce.requestDto;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Pergunta;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Produto;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Usuario;

import javax.validation.constraints.NotBlank;

public class PerguntaRequestDto {

    @NotBlank
    private String titulo;



    @Deprecated
    public PerguntaRequestDto() {
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public PerguntaRequestDto(String titulo) {
        this.titulo = titulo;
    }



    public Pergunta converteParaPergunta(Produto produto, Usuario usuario) {
        return new Pergunta(titulo, produto, usuario);
    }
}
