package com.demo.carbookingservice.service;

import java.util.Date;
import java.util.List;

import com.demo.carbookingservice.model.CarDetails;

public interface IBookingService {
	void associateDriver(Integer carId, Integer driverId);

	boolean checkDriverAvailability(Integer driverId);

	void updateStatus(Integer carId, String status);

	List<CarDetails> getCarListOfStatus(String status);

	void bookDriver(Integer carId, Integer driverId, Date startTime, Date endTime);

	List<CarDetails> getActivebookings();
}
