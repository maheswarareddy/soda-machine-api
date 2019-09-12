package com.soda.machine.api.machine.state.data;

import org.springframework.stereotype.Repository;

import com.soda.machine.api.data.BaseDao;
import com.soda.machine.api.machine.state.request.SodaMachineStateRequest;
import com.soda.machine.api.machine.status.model.SodaMachineStateBean;

@Repository
public class SodaMachineStateDao extends BaseDao {

	private static final String QUERY_INSERT_SODA_MACHINE_STATE = "INSERT into SodaMachineState(machine_id,state,money_inserted) VALUES (?,?, ?)";
	private static final String QUERY_UPDATE_SODA_MACHINE_STATE = "UPDATE SodaMachineState set state = ? , money_inserted= ? where machine_id = ?";
	private static final String QUERY_GET_SODA_MACHINE_STATE = "SELECT * from SodaMachineState where machine_id = ?";

	public Boolean saveSodaMachineState(SodaMachineStateRequest sodaMachineStateRequest) {
		return insert(QUERY_INSERT_SODA_MACHINE_STATE, new Object[] { sodaMachineStateRequest.getMachineId(),
				sodaMachineStateRequest.getState(), sodaMachineStateRequest.getMoneyInserted() });
	}

	public Boolean updateSodaMachineState(SodaMachineStateRequest sodaMachineStatusRequest) {
		return update(QUERY_UPDATE_SODA_MACHINE_STATE,
				new Object[] { sodaMachineStatusRequest.getState(), sodaMachineStatusRequest.getMoneyInserted(),sodaMachineStatusRequest.getMachineId() });
	}

	public SodaMachineStateBean getSodaMachineState(String machineId) {
		return queryForObject(QUERY_GET_SODA_MACHINE_STATE, new Object[] { machineId },
				new SodaMachineStateRowMapper());
	}
}
