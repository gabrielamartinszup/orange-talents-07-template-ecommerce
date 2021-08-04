package br.com.zupacademy.gabrielamartins.ecommerce.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(1)
    @Max(5)
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Usuario usuario;

    public Opiniao(Integer nota, String titulo, String descricao, Produto produto, Usuario usuario) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.usuario = usuario;
    }
}
