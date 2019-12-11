package com.demo.carbookingservice.dao.impl;

import java.sql.Types;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.carbookingservice.controller.BookingController;
import com.demo.carbookingservice.dao.IBookingDAO;
import com.demo.carbookingservice.entity.Car;

@Repository
@Transactional
public class BookingDAO implements IBookingDAO {

	private static final Logger LOGGER = LogManager.getLogger(BookingDAO.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	String assignDriver = "UPDATE car SET driver_id = ? WHERE id = ?";
	String bookDriverSql = "UPDATE car SET booking_start_time = ? , booking_end_time = ? ,"
			+ " driver_id = ? WHERE id =?";

	String getCarForDriver = "Select * from car where driver_id = ?";
	String updateStatus = "Update car set status = ? where id =?";

	@Override
	public void assignDriver(Integer carId, Integer driverId) {

		// define query arguments
		Object[] params = { driverId, carId };

		// define SQL types of the arguments
		int[] types = { Types.BIGINT, Types.BIGINT };

		int rows = jdbcTemplate.update(assignDriver, params, types);
		LOGGER.info("Number of rows updated : " + rows);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Car checkDriverAvailability(Integer driverId) {
		List cars = jdbcTemplate.query(getCarForDriver, new Object[] { driverId },
				new BeanPropertyRowMapper(Car.class));
		if (cars.size() == 1)
			return (Car) cars.get(0);

		return null;
	}

	@Override
	public void updateCarStatus(Integer carId, String status) {
		// define query arguments
		Object[] params = { status, carId };

		// define SQL types of the arguments
		int[] types = { Types.VARCHAR, Types.BIGINT };

		int rows = jdbcTemplate.update(updateStatus, params, types);
		LOGGER.info("Number of rows updated : " + rows);
	}

	@Override
	public void bookDriver(Integer carId, Integer driverId, Date startTime, Date endTime) {

		// define query arguments
		Object[] params = { startTime, endTime, driverId, carId };

		// define SQL types of the arguments
		int[] types = { Types.TIMESTAMP, Types.TIMESTAMP, Types.BIGINT, Types.BIGINT };
		
		int rows = jdbcTemplate.update(bookDriverSql, params, types);
		LOGGER.info("Booked driver for cars : " + rows);
	}

}
