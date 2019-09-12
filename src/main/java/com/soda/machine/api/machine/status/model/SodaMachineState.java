package com.soda.machine.api.machine.status.model;

public enum SodaMachineState {

	NO_QUARTERS("eject-coin", "no-quarters"), HAS_QUARTERS("insert-coin", "has-quarter"),
	SODA_SOLD_OUT("", "soda-sold-out"), SODA_SOLD("purchase-soda", "no-quarters");

	private String type;

	private String status;

	private SodaMachineState(String type, String status) {
		this.type = type;
		this.status = status;
	}

	public static String getStatus(String action) {
		for (SodaMachineState machineStatus : SodaMachineState.values()) {
			if (machineStatus.type.equals(action)) {
				return machineStatus.status;
			}
		}
		return "";
	}

	public String getType() {
		return type;
	}

	public String getState() {
		return status;
	}
}
