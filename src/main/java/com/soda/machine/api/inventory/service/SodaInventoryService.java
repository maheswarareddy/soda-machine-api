package com.soda.machine.api.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soda.machine.api.inventory.data.SodaInventoryDao;
import com.soda.machine.api.inventory.request.UpdateSodaRequest;
import com.soda.machine.api.inventory.response.AddSodaResponse;
import com.soda.machine.api.inventory.response.DeleteSodaResponse;
import com.soda.machine.api.inventory.response.UpdateSodaResponse;
import com.soda.machine.api.purchase.model.Soda;

@Service
public class SodaInventoryService {

	@Autowired
	private SodaInventoryDao inventoryDao;

	public AddSodaResponse addSoda(String machineId,Soda soda) {
		AddSodaResponse addSodaResponse=new AddSodaResponse();
		addSodaResponse.setSodaAdded(inventoryDao.addSoda(machineId, soda));
		return addSodaResponse;
	}

	public UpdateSodaResponse updateSodaQuantity(UpdateSodaRequest updateSodaRequest) {
		UpdateSodaResponse  updateSodaResponse=new UpdateSodaResponse();
		updateSodaResponse.setSodaUpdated(inventoryDao.updateSodaQuantity(updateSodaRequest));
		return updateSodaResponse;
	}

	public DeleteSodaResponse deleteSoda(String machineId,String brand) {
		DeleteSodaResponse deleteSodaResponse=new DeleteSodaResponse();
		deleteSodaResponse.setSodaDeleted(inventoryDao.deleteSoda(machineId,brand));
		return deleteSodaResponse;
	}

	public Soda getSoda(String machineId,String brand) {
		return inventoryDao.getSoda(machineId,brand);
	}
}
