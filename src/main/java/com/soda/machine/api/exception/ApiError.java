package com.soda.machine.api.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiError {

	private List<Error> errors;

	public ApiError(Error error) {
		errors = Arrays.asList(error);
	}

	public ApiError(List<Error> errors) {
		super();
		this.errors = errors;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

}