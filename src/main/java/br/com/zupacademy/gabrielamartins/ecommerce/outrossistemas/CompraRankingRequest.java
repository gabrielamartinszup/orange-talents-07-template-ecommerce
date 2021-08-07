package br.com.zupacademy.gabrielamartins.ecommerce.outrossistemas;

import javax.validation.constraints.NotNull;

public class CompraRankingRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idVendedor;

    public CompraRankingRequest(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    @Override
    public String toString() {
        return "CompraRankingRequest{" +
                "idCompra=" + idCompra +
                ", idUsuario=" + idVendedor +
                '}';
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }
}
