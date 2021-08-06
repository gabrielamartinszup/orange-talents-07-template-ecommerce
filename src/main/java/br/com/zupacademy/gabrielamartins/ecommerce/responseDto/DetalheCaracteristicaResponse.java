package br.com.zupacademy.gabrielamartins.ecommerce.responseDto;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Caracteristica;

public class DetalheCaracteristicaResponse {


    private String nome;
    private String descricao;

    public DetalheCaracteristicaResponse(Caracteristica caracteristica) {

        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
