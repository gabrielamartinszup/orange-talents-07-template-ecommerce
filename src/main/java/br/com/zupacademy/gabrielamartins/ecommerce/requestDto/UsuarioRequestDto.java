package br.com.zupacademy.gabrielamartins.ecommerce.requestDto;

import br.com.zupacademy.gabrielamartins.ecommerce.model.SenhaLimpa;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.swing.text.html.Option;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class UsuarioRequestDto {

    @NotBlank(message = "Email não pode ser em branco!")
    @Email(message = "Insira um formato de e-mail válido!")
    private String login;
    @NotBlank
    @Length(min = 6, message = "A senha deve ter pelo menos 6 caracteres!")
    private String senha;


    public UsuarioRequestDto(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario converteParaUsuario(){

        return new Usuario (login, new SenhaLimpa(senha));

    }
}
