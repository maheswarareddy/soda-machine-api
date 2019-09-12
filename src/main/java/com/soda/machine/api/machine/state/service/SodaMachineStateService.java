package com.soda.machine.api.machine.state.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soda.machine.api.machine.state.data.SodaMachineStateDao;
import com.soda.machine.api.machine.state.request.SodaMachineStateRequest;
import com.soda.machine.api.machine.state.response.SaveMachineStateResponse;
import com.soda.machine.api.machine.state.response.UpdateMachineStateResponse;
import com.soda.machine.api.machine.status.model.SodaMachineState;
import com.soda.machine.api.machine.status.model.SodaMachineStateBean;

@Service
public class SodaMachineStateService {

	@Autowired
	private SodaMachineStateDao sodaMachineStateDao;

	public SaveMachineStateResponse saveSodaMachineState(SodaMachineStateRequest machineStatusRequest) {
		machineStatusRequest.setState(getMachineState(machineStatusRequest.getAction()));
		if (StringUtils.equalsAnyIgnoreCase(SodaMachineState.HAS_QUARTERS.getState(),
				machineStatusRequest.getState())) {
			machineStatusRequest.setMoneyInserted(0.25);
		}
		
		SaveMachineStateResponse saveMachineStateResponse=new SaveMachineStateResponse();
		saveMachineStateResponse.setStateSaved(sodaMachineStateDao.saveSodaMachineState(machineStatusRequest));
		return saveMachineStateResponse;
	}

	public UpdateMachineStateResponse updateSodaMachineState(SodaMachineStateRequest machineStateRequest) {
		machineStateRequest.setState(getMachineState(machineStateRequest.getAction()));
		SodaMachineStateBean sodaMachineStateBean = getSodaMachineState(machineStateRequest.getMachineId());
		if (StringUtils.equalsIgnoreCase(machineStateRequest.getState(), SodaMachineState.HAS_QUARTERS.getState())
				&& StringUtils.equalsIgnoreCase(sodaMachineStateBean.getState(),
						SodaMachineState.HAS_QUARTERS.getState())) {
			machineStateRequest.setMoneyInserted(sodaMachineStateBean.getMoneyInserted() + 0.25);
		} else if (StringUtils.equalsIgnoreCase(machineStateRequest.getState(), SodaMachineState.NO_QUARTERS.getState())
				|| StringUtils.equalsIgnoreCase(machineStateRequest.getState(),
						SodaMachineState.SODA_SOLD.getState())) {
			machineStateRequest.setMoneyInserted(0.00);
		}
		
		UpdateMachineStateResponse updateMachineStateResponse=new UpdateMachineStateResponse();
		updateMachineStateResponse.setStateUpdated(sodaMachineStateDao.updateSodaMachineState(machineStateRequest));
		return updateMachineStateResponse;
	}

	public SodaMachineStateBean getSodaMachineState(String machineId) {
		return sodaMachineStateDao.getSodaMachineState(machineId);
	}

	private String getMachineState(String action) {
		return SodaMachineState.getStatus(action);
	}
}
