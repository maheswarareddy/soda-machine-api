package com.soda.machine.api.purchase.model;

public enum SodaPurchaseErrorCodes {

	INSUFFICIENT_CREDIT("insufficient-credit", "Insufficient Credit"), SODA_SOLD_OUT("sold-out", "Soda Soldout");

	private String errorCode;

	private String description;

	private SodaPurchaseErrorCodes(String errorCode, String description) {
		this.errorCode = errorCode;
		this.description = description;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getDescription() {
		return description;
	}

}
