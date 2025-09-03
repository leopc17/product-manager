package com.br.productmanager.infraestructure.validation.validators;

import com.br.productmanager.infraestructure.validation.annotation.EnumValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {

    private Enum<?>[] enumValues;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClass();
        enumValues = enumClass.getEnumConstants();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }

        if (value.isBlank()) {
            return false;
        }

        for (Enum<?> enumValue : enumValues) {
            if (enumValue.name().equalsIgnoreCase(value)) {
                return true;
            }
        }

        return false;
    }
}
