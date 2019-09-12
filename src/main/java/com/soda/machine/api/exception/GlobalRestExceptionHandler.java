package com.soda.machine.api.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Error> errors = new ArrayList<Error>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(new Error(error.getField(), error.getCode(), error.getDefaultMessage()));
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(new Error(error.getObjectName(), error.getCode(), error.getDefaultMessage()));
		}
		ApiError apiError = new ApiError(errors);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errorMessage = ex.getMethod() + " is Not supported";
		Error error = new Error("method.not.supported", errorMessage);
		ApiError apiError = new ApiError(error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String errorMessage = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
		Error error = new Error("No.handler.found", errorMessage);
		ApiError apiError = new ApiError(error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAllExceptions(Exception ex) {
		String errorMessage = "exeption occured. cause:"+ex.getMessage();
		Error error = new Error("unknown.error", errorMessage);
		ApiError apiError = new ApiError(error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
