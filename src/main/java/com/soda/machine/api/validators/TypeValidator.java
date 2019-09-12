package com.soda.machine.api.validators;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

public class TypeValidator implements ConstraintValidator<TypeValidation, String> {

	private Class<? extends Enum<?>> enumType;
	private String valuePropertyName;

	@Override
	public void initialize(TypeValidation typeValidation) {
		this.enumType = typeValidation.enumType();
		this.valuePropertyName = typeValidation.valuePropertyName();
	}

	@Override
	public boolean isValid(String type, ConstraintValidatorContext context) {
		boolean valid = false;
		try {
			PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(enumType, valuePropertyName);
			Method propertyGetterMethod = propertyDescriptor.getReadMethod();
			Method m = BeanUtils.findMethod(this.enumType, "values");

			Object[] values = (Object[]) m.invoke(null);

			for (Object enumInstance : values) {
				String value = (String) propertyGetterMethod.invoke(enumInstance);
				if (StringUtils.equals(value, type)) {
					valid = true;
					break;
				}

			}
		} catch (Exception e) {
		}
		return valid;
	}

}
