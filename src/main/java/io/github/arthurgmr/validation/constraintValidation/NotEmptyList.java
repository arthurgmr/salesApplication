package io.github.arthurgmr.validation.constraintValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import io.github.arthurgmr.rest.dto.ItemOrderDTO;

import java.util.List;


public class NotEmptyList implements ConstraintValidator<io.github.arthurgmr.validation.NotEmptyList, List<ItemOrderDTO>> {

    // check if list is empty;
    @Override
    public boolean isValid(List<ItemOrderDTO> list, ConstraintValidatorContext constraintValidatorContext) {
        return list != null && !list.isEmpty();
    }

    @Override
    public void initialize(io.github.arthurgmr.validation.NotEmptyList constraintAnnotation) {
    }
}
