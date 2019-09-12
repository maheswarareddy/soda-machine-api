package com.soda.machine.api.inventory.request;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
public class UpdateSodaRequest {
	
	@JsonIgnore
	private String machineId;

	@JsonIgnore
	private String brand;

	private int quantity;

	public String getBrand() {
		return brand;
	}	

	public String getMachineId() {
		return machineId;
	}



	public void setMachineId(String machineId) {
		this.machineId = machineId;
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
