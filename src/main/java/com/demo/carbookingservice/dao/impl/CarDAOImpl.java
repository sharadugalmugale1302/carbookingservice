package com.demo.carbookingservice.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.demo.carbookingservice.dao.ICarDAO;
import com.demo.carbookingservice.entity.Car;

@Repository
public class CarDAOImpl implements ICarDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addCar(Car car) {
		
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert
		    .withTableName("car")
		    .usingGeneratedKeyColumns("id");
		SqlParameterSource params = new MapSqlParameterSource()
		    .addValue("vin", car.getVin())
		    .addValue("status", car.getStatus());
		Number number = simpleJdbcInsert.executeAndReturnKey(params);
	}

	@Override
	public void updateCar(Car car) {
		// TODO Auto-generated method stub

	}

	@Override
	public Car getCar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCar(int id) {
		// TODO Auto-generated method stub

	}

}
