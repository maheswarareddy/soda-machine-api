package com.soda.machine.api.exception;


public class Error {

	private String fieldName;
	private String code;
	private String message;

	public Error(String fieldName, String code, String message) {
		this.fieldName = fieldName;
		this.code = code;
		this.message = message;
	}
	
	public Error(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}