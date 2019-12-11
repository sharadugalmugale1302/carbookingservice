package com.demo.carbookingservice.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.carbookingservice.model.CarDetails;
import com.demo.carbookingservice.model.DriverDetails;
import com.demo.carbookingservice.model.Response;
import com.demo.carbookingservice.service.ICarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/car")
@Api(value = "Car Booking Service", description = "Operations pertaining to Car in Car Booking Service")
public class CarController {

	private static final Logger LOGGER = LogManager.getLogger(CarController.class);

	@Autowired
	private ICarService carService;

	@PutMapping("/add")
	@ApiOperation(value = "Add new Car", response = Response.class)
	public Response addCar(@RequestBody CarDetails car) {
		LOGGER.info("Add new Car : " + car);
		carService.addCar(car);
		return new Response.ResponseBuilder().setResponseCode(200).setResponseMesage("Car entry added successfully")
				.build();

	}

	@PostMapping("/update")
	@ApiOperation(value = "Update new Car", response = Response.class)
	public Response updateCar(@RequestBody CarDetails car) {
		LOGGER.info("Update Car details : " + car);
		carService.updateCar(car);
		return new Response.ResponseBuilder().setResponseCode(200).setResponseMesage("Car entry updated successfully")
				.build();

	}

	@GetMapping("/get/{id}")
	@ApiOperation(value = "Get Car details", response = CarDetails.class)
	public CarDetails getCar(@PathVariable("id") int id) {
		LOGGER.info("Get Car details for ID : " + id);
		return carService.getCar(id);

	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "Delete Car details", response = Response.class)
	public Response deleteCar(@PathVariable("id") int id) {
		LOGGER.info("Delete Car details of Id " + id);
		carService.deleteCar(id);
		return new Response.ResponseBuilder().setResponseCode(200).setResponseMesage("Car entry deleted successfully")
				.build();

	}
}
