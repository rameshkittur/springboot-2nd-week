package com.ramesh.week2.mvc.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;


import java.util.List;

@Slf4j
public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation,String> {

    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext) {
        List<String>roles = List.of("USER","ADMIN");
        boolean isValid = roles.contains(inputRole);
        log.info("Input Role: " + inputRole + ", isValid: " + isValid); // Add logging

        return isValid;
    }
}
