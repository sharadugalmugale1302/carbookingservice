package com.demo.carbookingservice.dao;

import java.util.Date;

import com.demo.carbookingservice.entity.Car;

public interface IBookingDAO {

	void assignDriver(Integer carId, Integer driverId);

	Car checkDriverAvailability(Integer driverId);

	void updateCarStatus(Integer carId, String status);

	void bookDriver(Integer carId, Integer driverId, Date startTime, Date endTime);
}
