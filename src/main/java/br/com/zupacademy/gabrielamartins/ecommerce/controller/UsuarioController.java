package br.com.zupacademy.gabrielamartins.ecommerce.controller;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Usuario;
import br.com.zupacademy.gabrielamartins.ecommerce.repository.UsuarioRepository;
import br.com.zupacademy.gabrielamartins.ecommerce.requestDto.UsuarioRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {


    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarUsuario(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto){

        Usuario usuario = usuarioRequestDto.converteParaUsuario();

            usuarioRepository.save(usuario);
            return ResponseEntity.ok().build();
        }

    }

