package br.com.zupacademy.gabrielamartins.ecommerce.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Pergunta implements Comparable<Pergunta> {

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

    public LocalDateTime getMomentoCriacao() {
        return momentoCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pergunta pergunta = (Pergunta) o;
        return Objects.equals(titulo, pergunta.titulo) && Objects.equals(produto, pergunta.produto) && Objects.equals(usuario, pergunta.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, produto, usuario);
    }

    @Override
    public int compareTo(Pergunta o) {
        return this.titulo.compareTo(o.titulo);
    }

    public String getTitulo() {
        return titulo;
    }
}
