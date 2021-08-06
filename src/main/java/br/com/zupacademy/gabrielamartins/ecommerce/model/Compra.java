package br.com.zupacademy.gabrielamartins.ecommerce.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    private Produto produto;
    @ManyToOne
    @NotNull
    @Valid
    private Usuario usuario;
    @Positive
    private Integer quantidade;
    @Enumerated
    @NotNull
    private GatewayPagamento gateway;

    @Enumerated
    private StatusCompra status;


    @Deprecated
    public Compra() {
    }


    public Compra(Produto produto, Usuario usuario, Integer quantidade, GatewayPagamento gateway) {
        this.produto = produto;
        this.usuario = usuario;
        this.quantidade = quantidade;
        this.gateway = gateway;
        this.status = StatusCompra.INICIADA;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produto=" + produto +
                ", usuario=" + usuario +
                ", quantidade=" + quantidade +
                '}';
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public Long getId() {
        return id;
    }

    public String getUrlPagamento(){
        return gateway.gerarUrlRetorno(this.getId());
    }
}
