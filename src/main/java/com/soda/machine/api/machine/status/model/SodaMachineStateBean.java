package com.soda.machine.api.machine.status.model;

import org.apache.commons.lang3.StringUtils;

public class SodaMachineStateBean {

	private String machineId;

	private String status;

	private double moneyInserted;


	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getState() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getMoneyInserted() {
		return moneyInserted;
	}

	public void setMoneyInserted(double moneyInserted) {
		this.moneyInserted = moneyInserted;
	}
	
	public Boolean isSoldout() {
		return StringUtils.equalsIgnoreCase(status, SodaMachineState.SODA_SOLD_OUT.getState());
	}
}