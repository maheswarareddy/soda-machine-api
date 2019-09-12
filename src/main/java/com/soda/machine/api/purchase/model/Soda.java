package com.soda.machine.api.purchase.model;

import com.soda.machine.api.validators.NotBlank;

public class Soda {

	@NotBlank(message = "Invalid brand")
	private String brand;

	private double price;

	private int quantity;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}