package com.kakaopay.task.settlement.controller.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AmountsMatchTotalValidator.class)
public @interface AmountsMatchTotal {
    String message() default "Amounts do not match the total settlement amount";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
