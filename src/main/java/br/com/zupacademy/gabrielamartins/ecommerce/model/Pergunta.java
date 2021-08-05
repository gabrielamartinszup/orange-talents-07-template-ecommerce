package br.com.zupacademy.gabrielamartins.ecommerce.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;

    private LocalDateTime momentoCriacao;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Usuario usuario;



    @Deprecated
    public Pergunta() {
    }


    public Pergunta(String titulo, Produto produto, Usuario usuario) {
        this.titulo = titulo;
        this.momentoCriacao = LocalDateTime.now();
        this.produto = produto;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Usuario getDonoProduto() {
        return produto.getUsuario();
    }
}
