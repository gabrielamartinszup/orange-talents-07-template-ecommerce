package br.com.zupacademy.gabrielamartins.ecommerce.model;

import br.com.zupacademy.gabrielamartins.ecommerce.validation.UniqueValue;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Email
    private String login;
    @NotBlank
    @Length(min = 6)
    private String senha;
    @NotNull
    private LocalDateTime dataCadastro = LocalDateTime.now();

    public Usuario() {
    }

    public Usuario(String login, SenhaLimpa senhaLimpa) {

        Assert.isTrue(StringUtils.hasText(login), "O e-mail não pode ser vazio");
        Assert.notNull(senhaLimpa, "A senha não poder ser nula");

        this.login = login;
        this.senha = senhaLimpa.hash();

    }
}
