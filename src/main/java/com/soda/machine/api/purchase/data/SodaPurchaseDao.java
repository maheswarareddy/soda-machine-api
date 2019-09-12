package com.soda.machine.api.purchase.data;

import org.springframework.stereotype.Repository;

import com.soda.machine.api.data.BaseDao;
import com.soda.machine.api.purchase.model.SodaPurchaseInformation;

@Repository
public class SodaPurchaseDao extends BaseDao {

	private static final String QUERY_SAVE_PURCHASE_INFO = "INSERT into SodaPurchaseInformation(machine_id,brand,quantity,purchase_date) VALUES(?,?,?,now())";

	private static final String QUERY_GET_PURCHASE_INFO = "SELECT count(*) from SodaPurchaseInformation where machine_id= ? and brand = ?";

	public Boolean saveSodaPurchase(SodaPurchaseInformation purchaseInformation) {
		return insert(QUERY_SAVE_PURCHASE_INFO, new Object[] { purchaseInformation.getMachineId(),
				purchaseInformation.getBrand(), purchaseInformation.getQuantity() });
	}

	public SodaPurchaseInformation getSodaPurchaseInformation(String machineId, String brand) {
		int numberOfSodasSold = queryForObject(QUERY_GET_PURCHASE_INFO, new Object[] { machineId, brand },
				Integer.class);
		SodaPurchaseInformation sodaPurchaseInformation = new SodaPurchaseInformation();
		sodaPurchaseInformation.setBrand(brand);
		sodaPurchaseInformation.setMachineId(machineId);
		sodaPurchaseInformation.setQuantity(numberOfSodasSold);
		return sodaPurchaseInformation;
	}
}
