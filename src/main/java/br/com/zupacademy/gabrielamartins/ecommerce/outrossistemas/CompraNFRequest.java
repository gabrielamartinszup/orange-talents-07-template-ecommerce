package br.com.zupacademy.gabrielamartins.ecommerce.outrossistemas;

import javax.validation.constraints.NotNull;

public class CompraNFRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idComprador;

    public CompraNFRequest(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }


    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }

    @Override
    public String toString() {
        return "CompraNFRequest{" +
                "idCompra=" + idCompra +
                ", idComprador=" + idComprador +
                '}';
    }
}
