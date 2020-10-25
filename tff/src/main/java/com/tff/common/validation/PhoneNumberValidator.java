package com.tff.common.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(phoneField)) {
            return true;
        }
        return  phoneField.matches("[0-9]+")
                && (phoneField.length() > 8)
                && (phoneField.length() < 14);
    }
}
