package br.com.zupacademy.gabrielamartins.ecommerce.repository;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
