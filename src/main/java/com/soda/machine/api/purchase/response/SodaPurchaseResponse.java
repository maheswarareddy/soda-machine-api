package com.soda.machine.api.purchase.response;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class SodaPurchaseResponse {

	private String errorCode;

	public Boolean isSodaSold() {
		return StringUtils.isBlank(errorCode);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}