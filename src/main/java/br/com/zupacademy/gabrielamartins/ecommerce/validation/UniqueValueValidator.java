package br.com.zupacademy.gabrielamartins.ecommerce.validation;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String domainAttribute;
    private Class<?> klass;


    @PersistenceContext
    EntityManager manager;

    @Override
    public void initialize(UniqueValue param) {
        domainAttribute = param.fieldName();
        klass = param.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("Select 1 from " + klass.getName() + " where " + domainAttribute + " =:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1,
                        "Foi encontrado mais de um " + klass + " com o atributo " + domainAttribute + " = " + value);
        return list.isEmpty();
    }
}
