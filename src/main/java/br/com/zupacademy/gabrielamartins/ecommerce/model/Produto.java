package br.com.zupacademy.gabrielamartins.ecommerce.model;

import br.com.zupacademy.gabrielamartins.ecommerce.requestDto.CaracteristicaRequestDto;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private final Set<Caracteristica> caracteristicas = new HashSet<>();

    @NotBlank(message = "Insira uma descrição para o produto")
    private String descricao;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Categoria categoria;

    @NotBlank(message = "Insira um nome para o produto")
    private String nome;

    @NotNull(message = "Insira um valor para o produto")
    private BigDecimal valor;

    @NotNull(message = "Insira uma quantidade para o produto")
    private Integer quantidade;

    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(Set<CaracteristicaRequestDto> caracteristicaRequests, String descricao, Usuario usuario, String nome, BigDecimal valor,
                   Integer quantidade, Categoria categoria) {

        caracteristicaRequests.forEach(cr -> this.caracteristicas.add(cr.converter(this)));
        this.descricao = descricao;
        this.usuario = usuario;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.dataCriacao = LocalDateTime.now();

        Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no mínimo 3 características");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return nome.equals(produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }


    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream()
                .map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagens);
    }


    public boolean pertenceAoUsuario(String email) {
        return this.usuario.getEmail().equals(email);
    }


    public Usuario getUsuario() {
        return usuario;
    }
}
