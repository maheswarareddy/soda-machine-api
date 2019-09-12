package com.soda.machine.api.inventory.data;

import org.springframework.stereotype.Repository;

import com.soda.machine.api.data.BaseDao;
import com.soda.machine.api.inventory.request.UpdateSodaRequest;
import com.soda.machine.api.purchase.model.Soda;

@Repository
public class SodaInventoryDao extends BaseDao {

	private static final String QUERY_ADD_SODA = "INSERT into Soda(machine_id,brand,price,quantity) Values(?,?,?,?)";
	private static final String QUERY_UPDATE_SODA = "UPDATE  Soda SET quantity = ?  where brand= ? and machine_id=?";
	private static final String QUERY_GET_SODA = "Select * from Soda  where brand= ? and machine_id = ?";
	private static final String QUERY_DELETE_SODA = "DELETE  from Soda  where brand= ? and machine_id = ?";

	public Boolean addSoda(String machineId, Soda soda) {
		return insert(QUERY_ADD_SODA, new Object[] { machineId, soda.getBrand(), soda.getPrice(), soda.getQuantity() });
	}

	public Boolean updateSodaQuantity(UpdateSodaRequest updateSodaRequest) {
		return update(QUERY_UPDATE_SODA, new Object[] { updateSodaRequest.getQuantity(), updateSodaRequest.getBrand(),
				updateSodaRequest.getMachineId() });
	}

	public Boolean deleteSoda(String machineId, String brand) {
		return update(QUERY_DELETE_SODA, new Object[] { brand, machineId });
	}

	public Soda getSoda(String machineId, String brand) {
		try {
			return queryForObject(QUERY_GET_SODA, new Object[] { brand, machineId }, new SodaRowMapper());
		} catch (Exception e) {
			return new Soda();
		}
	}
}