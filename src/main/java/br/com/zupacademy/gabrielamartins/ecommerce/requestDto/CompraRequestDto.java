package br.com.zupacademy.gabrielamartins.ecommerce.requestDto;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Compra;
import br.com.zupacademy.gabrielamartins.ecommerce.model.GatewayPagamento;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Produto;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Usuario;
import br.com.zupacademy.gabrielamartins.ecommerce.repository.ProdutoRepository;
import br.com.zupacademy.gabrielamartins.ecommerce.validation.ExistsId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

public class CompraRequestDto {
    @Positive
    private Integer quantidade;
    @NotNull
    @ExistsId(domainClass = Produto.class, fieldName = "id", message = "Esse produto não foi encontrado")
    private Long idProduto;
    @NotNull
    private GatewayPagamento gateway;

    public CompraRequestDto(Integer quantidade, Long idProduto, GatewayPagamento gateway) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gateway = gateway;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }

    public Optional<Compra> converteParaCompra(ProdutoRepository produtoRepository, Usuario usuario) {
        Optional<Produto> produtoObj = produtoRepository.findById(idProduto);


        Produto produto = produtoObj.get();
        if(produto.abateQuantidade(quantidade)) {
            return Optional.of(new Compra(produto, usuario,quantidade, gateway));
        }

        return Optional.empty();
    }
}
