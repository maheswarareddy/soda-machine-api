package com.soda.machine.api.machine.state.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soda.machine.api.machine.status.model.SodaMachineState;
import com.soda.machine.api.validators.TypeValidation;

public class SodaMachineStateRequest {
	@JsonIgnore
	private String machineId;

	@TypeValidation(enumType = SodaMachineState.class,valuePropertyName = "type", message = "Invalid Soda machine action")
	private String action;

	@JsonIgnore
	private String state;

	@JsonIgnore
	private double moneyInserted = 0.00;

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getMoneyInserted() {
		return moneyInserted;
	}

	public void setMoneyInserted(double moneyInserted) {
		this.moneyInserted = moneyInserted;
	}

}