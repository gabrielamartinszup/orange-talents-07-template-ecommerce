package br.com.zupacademy.gabrielamartins.ecommerce.model.enums;

public enum GatewayPagamento {

    PAGSEGURO{


        @Override
        public String gerarUrlRetorno(Long idCompra){
            return "/pagseguro.com?returnId="+idCompra+"&redirectUrl=url-do-pagseguro";
        }
    },

    PAYPAL{

        @Override
        public String gerarUrlRetorno(Long idCompra){
            return "/paypal.com?buyerId="+idCompra+"&redirectUrl=url-do-paypal";
        }

    };



    public abstract String gerarUrlRetorno(Long idCompra);
}
