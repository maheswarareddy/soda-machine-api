package com.soda.machine.api.purchase.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.soda.machine.api.purchase.model.SodaPurchaseInformation;

public class SodaPurchaseInformationRowMapper implements RowMapper<SodaPurchaseInformation> {

	public SodaPurchaseInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
		SodaPurchaseInformation purchaseInformation = new SodaPurchaseInformation();
		purchaseInformation.setBrand(rs.getString("brand"));
		purchaseInformation.setQuantity(rs.getInt("quantity"));
		purchaseInformation.setMachineId(rs.getString("machine_id"));
		return purchaseInformation;
	}
}