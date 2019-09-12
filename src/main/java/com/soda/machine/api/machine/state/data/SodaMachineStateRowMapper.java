package com.soda.machine.api.machine.state.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.soda.machine.api.machine.status.model.SodaMachineStateBean;

public class SodaMachineStateRowMapper implements RowMapper<SodaMachineStateBean> {

	public SodaMachineStateBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		SodaMachineStateBean sodaMachineStateBean = new SodaMachineStateBean();
		sodaMachineStateBean.setMoneyInserted(rs.getDouble("money_inserted"));
		sodaMachineStateBean.setMachineId(rs.getString("machine_id"));
		sodaMachineStateBean.setStatus(rs.getString("state"));
		return sodaMachineStateBean;
	}

}
