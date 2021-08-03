package br.com.zupacademy.gabrielamartins.ecommerce.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @ManyToOne
    private Categoria categoriaMae;

    @Deprecated
    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(String nome, Categoria categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public void setCategoriaMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }
}
