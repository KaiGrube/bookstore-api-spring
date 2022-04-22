package org.grube.bookstoreapispring.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = Isbn13Validator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Isbn13Constraint {
    String message() default "Invalid isbn13";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
