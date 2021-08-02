package br.com.zupacademy.gabrielamartins.ecommerce.repository;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
