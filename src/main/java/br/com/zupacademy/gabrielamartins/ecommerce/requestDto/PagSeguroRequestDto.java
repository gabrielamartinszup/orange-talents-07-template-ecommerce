package br.com.zupacademy.gabrielamartins.ecommerce.requestDto;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Compra;
import br.com.zupacademy.gabrielamartins.ecommerce.component.RetornoGatewayPagamento;
import br.com.zupacademy.gabrielamartins.ecommerce.model.enums.StatusRetornoPagSeguro;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Transacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagSeguroRequestDto implements RetornoGatewayPagamento {

    @NotBlank
    private String idTransacao;
    @NotNull
    private StatusRetornoPagSeguro status;

    public PagSeguroRequestDto(String idTransacao, StatusRetornoPagSeguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public String toString() {
        return "PagSeguroRequestDto{" +
                "idTransacao='" + idTransacao + '\'' +
                ", status=" + status +
                '}';
    }

    public Transacao toTransacao(Compra compra){
        return new Transacao(status.normaliza(), idTransacao, compra);
    }
}
