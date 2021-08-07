package br.com.zupacademy.gabrielamartins.ecommerce.model;

import br.com.zupacademy.gabrielamartins.ecommerce.component.RetornoGatewayPagamento;
import br.com.zupacademy.gabrielamartins.ecommerce.model.enums.GatewayPagamento;
import br.com.zupacademy.gabrielamartins.ecommerce.model.enums.StatusCompra;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();


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
                ", gateway=" + gateway +
                ", status=" + status +
                ", transacoes=" + transacoes +
                '}';
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public Usuario getDonoProduto(){
        return produto.getUsuario();
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

    public StatusCompra getStatus() {
        return status;
    }

    public void adicionaTransacao(RetornoGatewayPagamento request) {
        Transacao novaTransacao = request.toTransacao(this);
        Assert.isTrue(!this.transacoes.contains(novaTransacao), "Essa transação já foi processada");

        Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(), "Essa compra já foi concluída com sucesso");

        this.transacoes.add(novaTransacao);
    }


    private Set<Transacao> transacoesConcluidasComSucesso(){
        Set <Transacao> transacoesConcluidasComSucesso = this.transacoes.stream().filter(Transacao :: concluidaComSucesso).collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1, "ERRO!! Tem mais de 1 transação concluída com sucesso aqui nessa compra com id " + this.id);

        return transacoesConcluidasComSucesso;
    }


    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }
}
