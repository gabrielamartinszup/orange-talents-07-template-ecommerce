package br.com.zupacademy.gabrielamartins.ecommerce.component;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Compra;

/**
 * Todo evente relacionado ao sucesso de uma nova compra deve implementar essa interface
 */
public interface EventoCompraSucesso {

    void processa(Compra compra);
}
