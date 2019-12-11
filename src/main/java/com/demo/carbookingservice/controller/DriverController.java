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

import com.demo.carbookingservice.model.DriverDetails;
import com.demo.carbookingservice.model.Response;
import com.demo.carbookingservice.service.IDriverService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/driver")
@Api(value = "Car Booking Service", description = "Operations pertaining to Driver in Car Booking Service")
public class DriverController {

	private static final Logger LOGGER = LogManager.getLogger(DriverController.class);
	@Autowired
	private IDriverService driverService;

	@PutMapping("/add")
	@ApiOperation(value = "Add new Driver", response = Response.class)
	public Response addDriver(@RequestBody DriverDetails driver) {
		LOGGER.info("Add new Driver details : " + driver);
		driverService.addDriver(driver);
		return new Response.ResponseBuilder().setResponseCode(200).setResponseMesage("Driver entry added successfully")
				.build();

	}

	@PostMapping("/update")
	@ApiOperation(value = "Update Driver details", response = Response.class)
	public Response updateDriver(@RequestBody DriverDetails driver) {
		LOGGER.info("Add update Driver details : " + driver);
		driverService.updateDriver(driver);
		return new Response.ResponseBuilder().setResponseCode(200)
				.setResponseMesage("Driver entry updated successfully").build();

	}

	@GetMapping("/get/{id}")
	@ApiOperation(value = "Get Driver details", response = DriverDetails.class)
	public DriverDetails getDriver(@PathVariable("id") int id) {
		LOGGER.info("Get Driver details for Id : " + id);
		return driverService.getDriver(id);

	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "Delete Driver details", response = Response.class)
	public Response deleteDriver(@PathVariable("id") int id) {
		LOGGER.info("Delete driver details for Id " + id);
		driverService.deleteDriver(id);
		return new Response.ResponseBuilder().setResponseCode(200)
				.setResponseMesage("Driver entry deleted successfully").build();

	}

}