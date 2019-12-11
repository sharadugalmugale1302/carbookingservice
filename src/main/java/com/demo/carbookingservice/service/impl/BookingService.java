package com.demo.carbookingservice.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.carbookingservice.dao.CarRepository;
import com.demo.carbookingservice.dao.IBookingDAO;
import com.demo.carbookingservice.entity.Car;
import com.demo.carbookingservice.model.CarDetails;
import com.demo.carbookingservice.service.IBookingService;

@Service
public class BookingService implements IBookingService {

	@Autowired
	private IBookingDAO bookingDAO;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CarRepository carRepository;
	
	@Override
	public void associateDriver(Integer carId, Integer driverId) {
		bookingDAO.assignDriver(carId, driverId);
	}
	@Override
	public boolean checkDriverAvailability(Integer driverId) {
		Car car = bookingDAO.checkDriverAvailability(driverId);
		return car == null;
	}
	@Override
	public void updateStatus(Integer carId, String status) {
		bookingDAO.updateCarStatus(carId,status);
	}
	@Override
	public List<CarDetails> getCarListOfStatus(String status) {
		Type listType = new TypeToken<List<CarDetails>>(){}.getType();
		List<Car> cars = carRepository.getCarsByStatus(status);
		if(null != cars) {
			return mapper.map(cars,listType);	
		}
		return new ArrayList<CarDetails>();
	}
	@Override
	public void bookDriver(Integer carId, Integer driverId, Date startTime, Date endTime) {
		bookingDAO.bookDriver(carId,driverId,startTime,endTime);
		
	}
	@Override
	public List<CarDetails> getActivebookings() {
		Type listType = new TypeToken<List<CarDetails>>(){}.getType();
		List<Car> cars = carRepository.getActiveBooking();
		if(null != cars) {
			return mapper.map(cars,listType);	
		}
		return new ArrayList<CarDetails>();
	}
}
