package br.com.zupacademy.gabrielamartins.ecommerce.requestDto;

import br.com.zupacademy.gabrielamartins.ecommerce.model.Categoria;
import br.com.zupacademy.gabrielamartins.ecommerce.repository.CategoriaRepository;
import br.com.zupacademy.gabrielamartins.ecommerce.validation.ExistsId;
import br.com.zupacademy.gabrielamartins.ecommerce.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


public class CategoriaRequestDto {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "O nome precisa ser único!")
    private String nome;

    @JsonProperty(value = "categoriaMae")
    @Positive
    @ExistsId(domainClass = Categoria.class, fieldName = "id", message = "O id da categoria mãe precisa ser válido!")
    private Long idCategoriaMae;



    public CategoriaRequestDto(String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }


    public Categoria converteParaCategoria(EntityManager manager) {
        Categoria categoria = new Categoria(nome);
        if(idCategoriaMae != null) {
            Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
            Assert.notNull(categoriaMae,"o id da categoria mãe precisa ser válido");
            categoria.setCategoriaMae(categoriaMae);
        }
        return categoria;
    }

}
