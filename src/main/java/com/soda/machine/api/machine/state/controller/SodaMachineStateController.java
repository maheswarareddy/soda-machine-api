package com.soda.machine.api.machine.state.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soda.machine.api.machine.state.request.SodaMachineStateRequest;
import com.soda.machine.api.machine.state.response.SaveMachineStateResponse;
import com.soda.machine.api.machine.state.response.UpdateMachineStateResponse;
import com.soda.machine.api.machine.state.service.SodaMachineStateService;
import com.soda.machine.api.machine.status.model.SodaMachineStateBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "{machineId}/state")
@Api(tags = { "MachineState" })
public class SodaMachineStateController {

	private SodaMachineStateService machineStateService;

	public SodaMachineStateController(SodaMachineStateService machineStatusService) {
		this.machineStateService = machineStatusService;
	}

	@PostMapping
	@ApiOperation(value = "save state of machine")
	public SaveMachineStateResponse saveMachineState(@PathVariable String machineId,
			@RequestBody @Valid SodaMachineStateRequest machineStatusRequest) {
		machineStatusRequest.setMachineId(machineId);
		return machineStateService.saveSodaMachineState(machineStatusRequest);
	}

	@PutMapping
	@ApiOperation(value = "update state of machine")
	public UpdateMachineStateResponse updateMachineState(@PathVariable String machineId,
			@RequestBody @Valid SodaMachineStateRequest machineStatusRequest) {
		machineStatusRequest.setMachineId(machineId);
		return machineStateService.updateSodaMachineState(machineStatusRequest);
	}

	@GetMapping
	@ApiOperation(value = "get state of machine")
	public SodaMachineStateBean getMachineState(@PathVariable String machineId) {
		return machineStateService.getSodaMachineState(machineId);
	}
}
