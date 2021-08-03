package br.com.zupacademy.gabrielamartins.ecommerce.validation;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Long> {

    private String domainAttribute;
    private Class<?> klass;


    @PersistenceContext
    EntityManager manager;


    @Override
    public void initialize(ExistsId param) {
        domainAttribute = param.fieldName();
        klass = param.domainClass();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) {
            return true;
        }
        Query query = manager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "Foi encontrado mais de um registro com o id="+value);
        return !list.isEmpty();
    }

}
