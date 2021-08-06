package br.com.zupacademy.gabrielamartins.ecommerce.responseDto;
;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Opinioes;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Produto;

import java.math.BigDecimal;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.DoubleStream;


public class DetalheProdutoResponse {



    private String descricao;
    private String nome;
    private BigDecimal valor;
    private SortedSet<String> perguntas;
    private Set<String> imagens;
    private Set<DetalheCaracteristicaResponse> caracteristicas;
    private int totalOpinioes;
    private Set<DetalheOpiniaoResponse> opinioes;
    private Double mediaNotas;




    @Deprecated
    public DetalheProdutoResponse() {
    }

    public DetalheProdutoResponse(Produto produto) {

        this.descricao = produto.getDescricao();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.caracteristicas = produto.mapCaracteristicas(caracteristica -> new DetalheCaracteristicaResponse(caracteristica));
        this.imagens = produto.mapImagens(imagem -> imagem.getLink());
        this.perguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());



        Opinioes opinioes = produto.getOpinioes();
        this.opinioes = opinioes.mapOpinioes(opiniao -> new DetalheOpiniaoResponse(opiniao));
        this.mediaNotas = opinioes.media();
        this.totalOpinioes = opinioes.totalOpinioes();


    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Set<DetalheCaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getImagens() {
        return imagens;
    }

    public Set<DetalheOpiniaoResponse> getOpinioes() {
        return opinioes;
    }

    public SortedSet<String> getPerguntas() {
        return perguntas;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public int getTotalOpinioes() {
        return totalOpinioes;
    }
}
