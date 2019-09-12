package com.soda.machine.api.inventory.response;

import org.springframework.stereotype.Component;

@Component
public class AddSodaResponse {

	private Boolean sodaAdded;

	public Boolean isSodaAdded() {
		return sodaAdded;
	}

	public void setSodaAdded(Boolean sodaAdded) {
		this.sodaAdded = sodaAdded;
	}

}
