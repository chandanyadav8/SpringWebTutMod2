package com.example.SpringWebTutMod2.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = {DepartmentNameEvenLengthValidator.class})
public @interface DepartmentNameEvenLengthValidation {
    String message() default "Department Name Length should only be even";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
