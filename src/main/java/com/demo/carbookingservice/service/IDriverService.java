package com.demo.carbookingservice.service;

import com.demo.carbookingservice.model.DriverDetails;

public interface IDriverService {

	void addDriver(DriverDetails Driver);
	void updateDriver(DriverDetails Driver);
	DriverDetails getDriver(int id);
	void deleteDriver(int id);
	DriverDetails getBookedDrivers();
}
