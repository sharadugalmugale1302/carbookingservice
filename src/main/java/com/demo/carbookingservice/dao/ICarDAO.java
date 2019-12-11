package com.demo.carbookingservice.dao;

import com.demo.carbookingservice.entity.Car;

public interface ICarDAO {

	void addCar(Car car);
	void updateCar(Car car);
	Car getCar(int id);
	void deleteCar(int id);
}
