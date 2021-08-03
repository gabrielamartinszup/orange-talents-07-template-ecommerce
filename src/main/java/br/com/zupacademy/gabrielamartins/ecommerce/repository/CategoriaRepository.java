package br.com.zupacademy.gabrielamartins.ecommerce.repository;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
