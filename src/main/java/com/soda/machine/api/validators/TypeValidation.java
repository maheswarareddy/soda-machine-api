package com.soda.machine.api.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = TypeValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeValidation {
	
	Class<? extends Enum<?>> enumType();
	
	String valuePropertyName();
	
	String message() default "invalid type";
	
	Class<?>[] groups() default {};
	 
	Class<? extends Payload>[] payload() default {};

}
