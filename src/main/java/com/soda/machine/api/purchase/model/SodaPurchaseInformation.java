package com.soda.machine.api.purchase.model;

import org.springframework.stereotype.Component;

@Component
public class SodaPurchaseInformation {

	private String machineId;

	private String brand;

	private int quantity;

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}