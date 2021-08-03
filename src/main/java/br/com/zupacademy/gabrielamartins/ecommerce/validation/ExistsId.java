package br.com.zupacademy.gabrielamartins.ecommerce.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ExistsIdValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsId {

    String message() default "{br.com.zupacademy.gabrielamartins.ecommerce.ExistsId}";
    Class<?> [] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String fieldName();
    Class<?> domainClass();
}
