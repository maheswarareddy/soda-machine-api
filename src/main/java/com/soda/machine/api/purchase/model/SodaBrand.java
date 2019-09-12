package com.soda.machine.api.purchase.model;

public enum SodaBrand {
	COKE("coke", "Coke"), PEPSI("pepsi", "Pepsi"), DIET_COKE("diet-coke", "Diet Coke"),
	DIET_PEPSI("diet-pepsi", "Diet Pepsi"), SPRITE("sprite", "Sprite");

	private String type;

	private String description;

	private SodaBrand(String type, String description) {
		this.type = type;
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

}
