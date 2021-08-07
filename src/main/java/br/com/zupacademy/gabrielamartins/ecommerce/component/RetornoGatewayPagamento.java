package br.com.zupacademy.gabrielamartins.ecommerce.component;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Compra;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Transacao;

public interface RetornoGatewayPagamento {


    /**
     * @param compra
     * @return uma nova transacao em funcao de um gateway especifico
     */
    Transacao toTransacao(Compra compra);
}
