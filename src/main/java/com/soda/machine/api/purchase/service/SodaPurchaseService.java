package com.soda.machine.api.purchase.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soda.machine.api.inventory.request.UpdateSodaRequest;
import com.soda.machine.api.inventory.service.SodaInventoryService;
import com.soda.machine.api.machine.state.request.SodaMachineStateRequest;
import com.soda.machine.api.machine.state.service.SodaMachineStateService;
import com.soda.machine.api.machine.status.model.SodaMachineState;
import com.soda.machine.api.machine.status.model.SodaMachineStateBean;
import com.soda.machine.api.purchase.data.SodaPurchaseDao;
import com.soda.machine.api.purchase.model.Soda;
import com.soda.machine.api.purchase.model.SodaPurchaseErrorCodes;
import com.soda.machine.api.purchase.model.SodaPurchaseInformation;
import com.soda.machine.api.purchase.request.SodaPurchaseRequest;
import com.soda.machine.api.purchase.response.SodaPurchaseResponse;

@Service
public class SodaPurchaseService {

	private static Logger LOG = LoggerFactory.getLogger(SodaPurchaseService.class);

	@Autowired
	private SodaInventoryService inventoryService;

	@Autowired
	private SodaMachineStateService sodaMachineStateService;

	@Autowired
	private SodaPurchaseDao sodaPurchaseDao;

	public SodaPurchaseResponse purchaseSoda(SodaPurchaseRequest purchaseRequest) {
		SodaPurchaseResponse purchaseResponse = new SodaPurchaseResponse();
		SodaMachineStateBean machineState = sodaMachineStateService.getSodaMachineState(purchaseRequest.getMachineId());
		Soda soda = inventoryService.getSoda(purchaseRequest.getMachineId(), purchaseRequest.getBrand());
		if (soda.getQuantity() > purchaseRequest.getQuantity() && machineState.getMoneyInserted() >= soda.getPrice()) {
			LOG.debug("Inside purchase soda block");
			saveSodaPurchaseInformation(getSodaPurchaseInformation(purchaseRequest));
			updateSodaMachineState(purchaseRequest.getMachineId());
			updateSodaQuantity(purchaseRequest, soda);
			return purchaseResponse;
		} else if (machineState.getMoneyInserted() < soda.getPrice()) {
			purchaseResponse.setErrorCode(SodaPurchaseErrorCodes.INSUFFICIENT_CREDIT.getErrorCode());
			return purchaseResponse;
		}
		purchaseResponse.setErrorCode(SodaPurchaseErrorCodes.SODA_SOLD_OUT.getErrorCode());
		return purchaseResponse;
	}

	public Boolean saveSodaPurchaseInformation(SodaPurchaseInformation purchaseInformation) {
		return sodaPurchaseDao.saveSodaPurchase(purchaseInformation);
	}

	public SodaPurchaseInformation getSodaPurchaseInformation(String machineId, String brand) {
		return sodaPurchaseDao.getSodaPurchaseInformation(machineId, brand);
	}

	private SodaPurchaseInformation getSodaPurchaseInformation(SodaPurchaseRequest purchaseRequest) {
		SodaPurchaseInformation purchaseInformation = new SodaPurchaseInformation();
		purchaseInformation.setBrand(purchaseRequest.getBrand());
		purchaseInformation.setMachineId(purchaseRequest.getMachineId());
		purchaseInformation.setQuantity(purchaseRequest.getQuantity());
		return purchaseInformation;
	}

	private void updateSodaMachineState(String machineId) {
		SodaMachineStateRequest sodaMachineStateRequest = new SodaMachineStateRequest();
		sodaMachineStateRequest.setMachineId(machineId);
		sodaMachineStateRequest.setAction(SodaMachineState.SODA_SOLD.getType());
		sodaMachineStateService.updateSodaMachineState(sodaMachineStateRequest);
	}

	private void updateSodaQuantity(SodaPurchaseRequest purchaseRequest, Soda soda) {
		UpdateSodaRequest updateSodaRequest = new UpdateSodaRequest();
		updateSodaRequest.setMachineId(purchaseRequest.getMachineId());
		updateSodaRequest.setBrand(purchaseRequest.getBrand());
		updateSodaRequest.setPrice(soda.getPrice());
		updateSodaRequest.setQuantity(soda.getQuantity() - purchaseRequest.getQuantity());
		inventoryService.updateSoda(updateSodaRequest);
	}
}