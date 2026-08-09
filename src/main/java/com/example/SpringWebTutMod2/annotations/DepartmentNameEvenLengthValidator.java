package com.example.SpringWebTutMod2.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DepartmentNameEvenLengthValidator implements ConstraintValidator<DepartmentNameEvenLengthValidation,String> {
    @Override
    public boolean isValid(String departmentName, ConstraintValidatorContext constraintValidatorContext) {
        if(departmentName.length()%2==0)
            return true;
        return false;
    }
}
