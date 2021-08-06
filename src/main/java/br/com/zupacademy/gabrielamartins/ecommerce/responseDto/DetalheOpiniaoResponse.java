package br.com.zupacademy.gabrielamartins.ecommerce.responseDto;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Opiniao;

public class DetalheOpiniaoResponse{

    private String titulo;
    private String descricao;
    private Double nota;

    public DetalheOpiniaoResponse(Opiniao opiniao) {
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.nota = opiniao.getNota();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
