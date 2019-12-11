package com.demo.carbookingservice.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.carbookingservice.model.CarDetails;
import com.demo.carbookingservice.model.Response;
import com.demo.carbookingservice.service.IBookingService;
import com.demo.carbookingservice.service.ICarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/booking")
@Api(value = "Car Booking Service", description = "Operations pertaining to Car Booking in Car Booking Service")
public class BookingController {

	private static final Logger LOGGER = LogManager.getLogger(BookingController.class);
	@Autowired
	private ICarService carService;

	@Autowired
	private IBookingService bookingService;

	@PostMapping("/assignDriver")
	@ApiOperation(value = "Associate a Car with selected Driver", response = Response.class)
	public Response associateDriver(@RequestParam("carId") Integer carId, @RequestParam("driverId") Integer driverId) {
		LOGGER.info("Assign driver id  : " + driverId + " Car Id : " + carId);
		try {
			boolean isDriverFree = bookingService.checkDriverAvailability(driverId);
			if (!isDriverFree) {
				return new Response.ResponseBuilder().setResponseCode(400)
						.setResponseMesage("Driver is already assigned to Car").build();
			}
			bookingService.associateDriver(carId, driverId);
			return new Response.ResponseBuilder().setResponseCode(200).setResponseMesage("Driver assigned to Car")
					.build();
		} catch (Exception e) {
			return new Response.ResponseBuilder().setResponseCode(500).setResponseMesage(e.getMessage()).build();
		}

	}

	@GetMapping("/getActiveBookings")
	@ApiOperation(value = "Get all Active booking", response = List.class)
	public List<CarDetails> getActiveBookings() {
		LOGGER.info("getActiveBookings for : " + new Date());
		return bookingService.getActivebookings();
	}

	@PostMapping("/updateStatus")
	@ApiOperation(value = "Update Car Status", response = Response.class)
	public Response updateStatus(@RequestParam("carId") Integer carId, @RequestParam("status") String status) {
		LOGGER.info("update Status for Car ID : " + carId + "Status : " + status);
		try {
			bookingService.updateStatus(carId, status);
			return new Response.ResponseBuilder().setResponseCode(200)
					.setResponseMesage("Car status updated Successfully").build();
		} catch (Exception e) {
			LOGGER.error("Error while updating status : " + e);
			return new Response.ResponseBuilder().setResponseCode(500)
					.setResponseMesage("Error while updating car status : " + e.getMessage()).build();
		}

	}

	@GetMapping("/getCarListOfStatus")
	@ApiOperation(value = "Get Car List for given Status", response = List.class)
	public List<CarDetails> getCarListOfStatus(@RequestParam("status") String status) {
		LOGGER.info(" Car List for status : " + status);
		return bookingService.getCarListOfStatus(status);
	}

	@PostMapping("/bookDriver")
	@ApiOperation(value = "Book driver for specific Car for given Time window", response = List.class)
	public Response updateStatus(@RequestParam("carId") Integer carId, @RequestParam("driverId") Integer driverId,
			@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime) {

		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			// replace with your start date string
			Date startDate = df.parse(startTime);
			Date endDate = df.parse(endTime);
			bookingService.bookDriver(carId, driverId, startDate, endDate);
			return new Response.ResponseBuilder().setResponseCode(200).setResponseMesage("Driver booked Successfully")
					.build();

		} catch (Exception e) {
			return new Response.ResponseBuilder().setResponseCode(500)
					.setResponseMesage("Error while Booking Driver : " + e.getMessage()).build();
		}

	}

	@GetMapping("/getBookedDrivers")
	@ApiOperation(value = "Fetch list of drivers along with their car details", response = List.class)
	public List<CarDetails> getBookedDrivers() {
		return carService.getBookedDrivers();
	}
}
