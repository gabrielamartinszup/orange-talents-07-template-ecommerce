package br.com.zupacademy.gabrielamartins.ecommerce.requestDto;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Compra;
import br.com.zupacademy.gabrielamartins.ecommerce.component.RetornoGatewayPagamento;
import br.com.zupacademy.gabrielamartins.ecommerce.model.enums.StatusTransacao;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PaypalRequestDto implements RetornoGatewayPagamento {

    @Min(0)
    @Max(1)
    private int status;
    @NotBlank
    private String idTransacao;


    public PaypalRequestDto(int status, String idTransacao) {
        this.status = status;
        this.idTransacao = idTransacao;
    }

    public Transacao toTransacao(Compra compra){

        StatusTransacao statusCalculado = this.status == 0 ? StatusTransacao.erro : StatusTransacao.sucesso;
        return new Transacao(statusCalculado, idTransacao, compra);
    }
}
