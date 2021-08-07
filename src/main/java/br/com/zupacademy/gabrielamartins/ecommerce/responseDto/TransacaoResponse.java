package br.com.zupacademy.gabrielamartins.ecommerce.responseDto;

import br.com.zupacademy.gabrielamartins.ecommerce.model.enums.StatusTransacao;
import com.fasterxml.jackson.annotation.JsonCreator;

public class TransacaoResponse {

    private StatusTransacao statusTransacao;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public TransacaoResponse(StatusTransacao statusTransacao) {
        this.statusTransacao = statusTransacao;
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }
}
