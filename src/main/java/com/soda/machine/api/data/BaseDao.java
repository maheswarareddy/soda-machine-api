package com.soda.machine.api.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Boolean insert(String sql, Object[] args) {
		return jdbcTemplate.update(sql, args) > 0;
	}

	public Boolean update(String sql, Object[] args) {
		return jdbcTemplate.update(sql, args) > 0;
	}

	public <T> T queryForObject(String sql, Object[] args, RowMapper<T> mapper) {
		return jdbcTemplate.queryForObject(sql, args, mapper);
	}

	public <T> T queryForObject(String sql, Object[] args, Class<T> type) {
		return jdbcTemplate.queryForObject(sql, args, type);
	}

	public <T> List<T> query(String sql, Object[] args, RowMapper<T> mapper) {
		return jdbcTemplate.query(sql, args, mapper);
	}
}