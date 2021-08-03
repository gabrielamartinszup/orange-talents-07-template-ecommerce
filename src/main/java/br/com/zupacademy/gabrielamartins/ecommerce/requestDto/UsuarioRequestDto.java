package br.com.zupacademy.gabrielamartins.ecommerce.requestDto;

import br.com.zupacademy.gabrielamartins.ecommerce.model.SenhaLimpa;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Usuario;
import br.com.zupacademy.gabrielamartins.ecommerce.validation.UniqueValue;
import org.hibernate.validator.constraints.Length;

import javax.swing.text.html.Option;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class UsuarioRequestDto {

    @NotBlank(message = "Email não pode ser em branco!")
    @Email(message = "Insira um formato de e-mail válido!")
    @UniqueValue(domainClass = Usuario.class, fieldName = "email", message = "Já existe um usuário com esse e-mail cadastrado!")
    private String email;
    @NotBlank
    @Length(min = 6, message = "A senha deve ter pelo menos 6 caracteres!")
    private String senha;


    public UsuarioRequestDto(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario converteParaUsuario(){

        return new Usuario (email, new SenhaLimpa(senha));

    }
}
