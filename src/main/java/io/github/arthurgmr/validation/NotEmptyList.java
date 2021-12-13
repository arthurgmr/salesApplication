package io.github.arthurgmr.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = io.github.arthurgmr.validation.constraintValidation.NotEmptyList.class)
public @interface NotEmptyList {

    String message() default "The list can't be empty!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
