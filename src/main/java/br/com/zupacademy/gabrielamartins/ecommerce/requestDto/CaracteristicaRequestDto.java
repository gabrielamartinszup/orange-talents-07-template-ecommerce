package br.com.zupacademy.gabrielamartins.ecommerce.requestDto;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Caracteristica;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Produto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CaracteristicaRequestDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public CaracteristicaRequestDto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Caracteristica converter(Produto produto) {
        return new Caracteristica(nome, descricao, produto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristicaRequestDto that = (CaracteristicaRequestDto) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
