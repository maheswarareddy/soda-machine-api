package com.soda.machine.api.inventory.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.soda.machine.api.purchase.model.Soda;

public class SodaRowMapper implements RowMapper<Soda> {
	public Soda mapRow(ResultSet rs, int rowNum) throws SQLException {
		Soda soda = new Soda();
		soda.setBrand(rs.getString("brand"));
		soda.setPrice(rs.getDouble("price"));
		soda.setQuantity(rs.getInt("quantity"));
		return soda;
	}
}
