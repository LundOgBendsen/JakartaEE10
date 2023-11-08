package com.recipes.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Constraint(validatedBy = DecimalValidator.class)
@Documented
@Target({ METHOD, FIELD })
@Retention(RUNTIME)

public @interface DecimalValid {
    public double min();
    public double max();
	public double delta() default 1e-6;
    public String message() default "Invalid decimal value.";

    public Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
