package br.com.zupacademy.gabrielamartins.ecommerce.model;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Classe que isola operações sobre um conjunto de opinioes. Serve como auxiliar da classe Opiniao.
 */
public class Opinioes {

    private Set<Opiniao> opinioes;

    public Opinioes(Set<Opiniao> opinioes){
        this.opinioes = opinioes;
    }

    public <T> Set<T> mapOpinioes(Function<Opiniao, T> funcaoMapeadora){
        return this.opinioes.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public Double media() {
        Set<Double> notas = mapOpinioes(opiniao -> opiniao.getNota());

        OptionalDouble possivelMedia = notas.stream().mapToDouble(nota -> nota).average();

        return possivelMedia.orElse(0.0);
    }

    public int totalOpinioes(){
        return this.opinioes.size();
    }
}
