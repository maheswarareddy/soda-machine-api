package com.soda.machine.api.inventory.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soda.machine.api.inventory.request.UpdateSodaRequest;
import com.soda.machine.api.inventory.response.AddSodaResponse;
import com.soda.machine.api.inventory.response.DeleteSodaResponse;
import com.soda.machine.api.inventory.response.UpdateSodaResponse;
import com.soda.machine.api.inventory.service.SodaInventoryService;
import com.soda.machine.api.purchase.model.Soda;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/{machineId}/inventory")
@Api(tags = { "Inventory" })
public class SodaInventoryController {

	private SodaInventoryService sodaInventoryService;

	public SodaInventoryController(SodaInventoryService sodaInventoryService) {
		this.sodaInventoryService = sodaInventoryService;
	}

	@PostMapping(path = "/soda")
	@ApiOperation(value = "add soda to inventory")
	public AddSodaResponse addSoda(@PathVariable String machineId,@RequestBody @Valid Soda soda) {
		return sodaInventoryService.addSoda(machineId,soda);
	}

	@PutMapping(path = "/soda/{brand}")
	@ApiOperation(value = "update soda quantity")
	public UpdateSodaResponse updateSoda(@PathVariable String machineId,@PathVariable String brand,@RequestBody @Valid UpdateSodaRequest updateSodaRequest) {
		updateSodaRequest.setMachineId(machineId);
		updateSodaRequest.setBrand(brand);
		return sodaInventoryService.updateSoda(updateSodaRequest);

	}

	@DeleteMapping(path = "/soda/{brand}")
	@ApiOperation(value = "delete soda from inventory")
	public DeleteSodaResponse deleteSoda(@PathVariable String machineId,@PathVariable String brand) {
		return sodaInventoryService.deleteSoda(machineId,brand);
		
	}

	@GetMapping(path = "/soda/{brand}")
	@ApiOperation(value = "Get soda details from inventory")
	public Soda getSoda(@PathVariable String machineId,@PathVariable String brand) {
		return sodaInventoryService.getSoda(machineId, brand);
	}
}
