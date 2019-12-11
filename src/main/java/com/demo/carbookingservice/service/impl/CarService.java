package com.demo.carbookingservice.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.carbookingservice.dao.CarRepository;
import com.demo.carbookingservice.entity.Car;
import com.demo.carbookingservice.model.CarDetails;
import com.demo.carbookingservice.service.ICarService;

@Service
public class CarService implements ICarService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	CarRepository repository;

	@Override
	public void addCar(CarDetails car) {
		repository.save(mapper.map(car, Car.class));
	}

	@Override
	public void updateCar(CarDetails carDetails) {
		Optional<Car> car = repository.findById(carDetails.getId());
		if (car.isPresent()) {
			Car newEntity = car.get();
			newEntity.setVin(carDetails.getVin());
			newEntity.setStatus(carDetails.getStatus());
			newEntity = repository.save(newEntity);
		} else {
			repository.save(mapper.map(carDetails, Car.class));

		}
	}

	@Override
	public CarDetails getCar(int id) {
		Optional<Car> employee = repository.findById(id);

		if (employee.isPresent()) {
			return mapper.map(employee.get(), CarDetails.class);
		}
		return null;
	}

	public List<CarDetails> getAllCar() {
		Type listType = new TypeToken<List<CarDetails>>() {
		}.getType();
		List<CarDetails> cars = mapper.map(repository.findAll(), listType);

		if (cars.size() > 0) {
			return cars;
		} else {
			return new ArrayList<CarDetails>();
		}
	}

	@Override
	public void deleteCar(int id) {
		Optional<Car> employee = repository.findById(id);

		if (employee.isPresent()) {
			repository.deleteById(id);
		}
	}

	@Override
	public List<CarDetails> getBookedDrivers() {
		Type listType = new TypeToken<List<CarDetails>>() {
		}.getType();
		List<CarDetails> cars = mapper.map(repository.getBookedCars(), listType);

		if (cars.size() > 0) {
			return cars;
		} else {
			return new ArrayList<CarDetails>();
		}

	}

}
