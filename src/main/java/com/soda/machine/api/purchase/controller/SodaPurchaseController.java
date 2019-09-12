package com.soda.machine.api.purchase.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soda.machine.api.purchase.model.SodaPurchaseInformation;
import com.soda.machine.api.purchase.request.SodaPurchaseRequest;
import com.soda.machine.api.purchase.response.SodaPurchaseResponse;
import com.soda.machine.api.purchase.service.SodaPurchaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "{machineId}/soda/purchase")
@Api(tags = { "Purchase" })
public class SodaPurchaseController {

	private SodaPurchaseService sodaPurchaseService;

	public SodaPurchaseController(SodaPurchaseService sodaPurchaseService) {
		this.sodaPurchaseService = sodaPurchaseService;
	}

	@PostMapping
	@ApiOperation(value = "purchase soda")
	public SodaPurchaseResponse purchaseSoda(@PathVariable String machineId,
			@RequestBody @Valid SodaPurchaseRequest purchaseRequest) {
		purchaseRequest.setMachineId(machineId);
		return sodaPurchaseService.purchaseSoda(purchaseRequest);
	}

	@GetMapping(path = "/information/{brand}")
	@ApiOperation(value = "get soda purchase Information")
	public SodaPurchaseInformation getSodaPurchaseInformation(@PathVariable String machineId,
			@PathVariable String brand) {
		return sodaPurchaseService.getSodaPurchaseInformation(machineId, brand);
	}
}