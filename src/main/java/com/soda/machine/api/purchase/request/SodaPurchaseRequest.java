package com.soda.machine.api.purchase.request;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soda.machine.api.validators.NotBlank;

@Component
public class SodaPurchaseRequest {

	@JsonIgnore
	private String machineId;

	@NotBlank(message = "Invalid brand")
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