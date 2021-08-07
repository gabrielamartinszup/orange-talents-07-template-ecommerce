package br.com.zupacademy.gabrielamartins.ecommerce.controller;

import br.com.zupacademy.gabrielamartins.ecommerce.service.Emails;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Pergunta;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Produto;
import br.com.zupacademy.gabrielamartins.ecommerce.model.Usuario;
import br.com.zupacademy.gabrielamartins.ecommerce.repository.PerguntaRepository;
import br.com.zupacademy.gabrielamartins.ecommerce.repository.ProdutoRepository;
import br.com.zupacademy.gabrielamartins.ecommerce.requestDto.PerguntaRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PerguntaController {

    @Autowired
    PerguntaRepository perguntaRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    Emails emails;


    @PostMapping(value = "/produtos/{id}/perguntas")
    @Transactional
    public ResponseEntity<?> cadastrarPergunta(@Valid @RequestBody PerguntaRequestDto request, @PathVariable  Long id, @AuthenticationPrincipal Usuario usuario){

        Optional<Produto> produtoObject = produtoRepository.findById(id);
        if(produtoObject.isPresent()){
            Produto produto = produtoObject.get();

            Pergunta pergunta = request.converteParaPergunta(produto, usuario);
            perguntaRepository.save(pergunta);

            emails.novaPergunta(pergunta);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
