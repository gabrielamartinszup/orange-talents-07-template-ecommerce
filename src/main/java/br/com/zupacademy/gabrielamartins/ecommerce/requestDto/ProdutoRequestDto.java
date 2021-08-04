package br.com.zupacademy.gabrielamartins.ecommerce.requestDto;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Caracteristica;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Categoria;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Produto;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Usuario;
import br.com.zupacademy.gabrielamartins.ecommerce.repository.CategoriaRepository;
import br.com.zupacademy.gabrielamartins.ecommerce.repository.ProdutoRepository;
import br.com.zupacademy.gabrielamartins.ecommerce.validation.ExistsId;
import br.com.zupacademy.gabrielamartins.ecommerce.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoRequestDto {

    @NotBlank
    @UniqueValue(domainClass = Produto.class, fieldName = "nome", message = "Já existe um produto com esse nome")
    private String nome;

    @NotNull
    @Min(1)
    private BigDecimal valor;

    @NotNull
    @Min(0)
    private Integer quantidade;

    @JsonProperty(value = "caracteristicas")
    @Size(min = 3, message = "Cadastre no mínimo 3 características com nomes diferentes")
    private Set<CaracteristicaRequestDto> caracteristicaRequestDto;

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @JsonProperty(value = "categoria")
    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id", message = "Insira uma categoria válida")
    private Long categoriaId;

    public ProdutoRequestDto(String nome, BigDecimal valor, Integer quantidade, Set<CaracteristicaRequestDto> caracteristicaRequestDto,
                             String descricao, Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicaRequestDto = caracteristicaRequestDto;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }

    public Optional<Produto> converteParaProduto(CategoriaRepository categoriaRepository, Usuario usuario) {
        Optional<Categoria> categoriaObj = categoriaRepository.findById(categoriaId);
        Categoria categoria = categoriaObj.get();

        Produto produto = new Produto(caracteristicaRequestDto, descricao, usuario, nome, valor, quantidade, categoria);

        return Optional.of(produto);
    }
}
