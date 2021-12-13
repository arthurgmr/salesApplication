package io.github.arthurgmr.validation.constraintValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;


public class NotEmptyList implements ConstraintValidator<io.github.arthurgmr.validation.NotEmptyList, List> {

    // check if list is empty;
    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        return list != null && !list.isEmpty();
    }

    @Override
    public void initialize(io.github.arthurgmr.validation.NotEmptyList constraintAnnotation) {
    }
}
