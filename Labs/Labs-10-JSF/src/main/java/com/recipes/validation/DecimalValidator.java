package com.recipes.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DecimalValidator implements ConstraintValidator<DecimalValid, Double>  {
	private DecimalValid annotation;

	@Override
	public void initialize(DecimalValid constraintAnnotation) {
		this.annotation=constraintAnnotation;
	}

	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context) {
		//if too low
		if (value < annotation.min() - annotation.delta()) {
			return false;
		}
		//if too high
		if (value > annotation.max() + annotation.delta()) {
			return false;
		}
		return true;
	}
}
