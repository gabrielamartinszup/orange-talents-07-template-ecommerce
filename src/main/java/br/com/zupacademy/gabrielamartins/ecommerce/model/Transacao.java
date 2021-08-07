package br.com.zupacademy.gabrielamartins.ecommerce.model;

import br.com.zupacademy.gabrielamartins.ecommerce.model.enums.StatusTransacao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Compra compra;
    @NotNull
    private StatusTransacao status;
    private String idTransacaoGateway;
    private LocalDateTime instanteTransacao;

    @Deprecated
    public Transacao() {
    }

    public Transacao(StatusTransacao status, String idTransacaoGateway, Compra compra) {
        this.status = status;
        this.idTransacaoGateway = idTransacaoGateway;
        this.instanteTransacao = LocalDateTime.now();
        this.compra = compra;
    }

    public boolean concluidaComSucesso(){
        return this.status.equals(StatusTransacao.sucesso);
    }


    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", status=" + status +
                ", idTransacaoGateway='" + idTransacaoGateway + '\'' +
                ", instanteTransacao=" + instanteTransacao +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(idTransacaoGateway, transacao.idTransacaoGateway);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public StatusTransacao getStatus() {
        return status;
    }
}
