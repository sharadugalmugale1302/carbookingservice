package com.demo.carbookingservice.service;

import java.util.List;

import com.demo.carbookingservice.model.CarDetails;

public interface ICarService {

	void addCar(CarDetails car);
	void updateCar(CarDetails car);
	CarDetails getCar(int id);
	void deleteCar(int id);
	List<CarDetails> getBookedDrivers();
}
