package com.soda.machine.api.machine.state.response;

import org.springframework.stereotype.Component;

@Component
public class SaveMachineStateResponse {

	private Boolean stateSaved;

	public Boolean getStateSaved() {
		return stateSaved;
	}

	public void setStateSaved(Boolean stateSaved) {
		this.stateSaved = stateSaved;
	}

}
