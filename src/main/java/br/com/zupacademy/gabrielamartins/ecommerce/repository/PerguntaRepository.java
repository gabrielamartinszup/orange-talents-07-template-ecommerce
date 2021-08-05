package br.com.zupacademy.gabrielamartins.ecommerce.repository;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
}
