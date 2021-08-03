package br.com.zupacademy.gabrielamartins.ecommerce.controller;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Categoria;
import br.com.zupacademy.gabrielamartins.ecommerce.repository.CategoriaRepository;
import br.com.zupacademy.gabrielamartins.ecommerce.requestDto.CategoriaRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {


    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> salvar(@RequestBody @Valid CategoriaRequestDto request){
        Categoria categoria = request.converteParaCategoria(manager);
        manager.persist(categoria);
        return ResponseEntity.ok().build();
    }
}
