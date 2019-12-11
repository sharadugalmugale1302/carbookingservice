package com.demo.carbookingservice.service.impl;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.carbookingservice.dao.DriverRepository;
import com.demo.carbookingservice.entity.Driver;
import com.demo.carbookingservice.model.DriverDetails;
import com.demo.carbookingservice.service.IDriverService;

@Service
public class DriverService implements IDriverService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	DriverRepository repository;

	@Override
	public void addDriver(DriverDetails Driver) {
		repository.save(mapper.map(Driver, Driver.class));
	}

	@Override
	public void updateDriver(DriverDetails driverDetails) {
		Optional<Driver> driver = repository.findById(driverDetails.getId());
		if(driver.isPresent()) 
        {
            Driver newEntity = driver.get();
            newEntity.setMobileNumber(driverDetails.getMobileNumber());
            newEntity.setFirstName(driverDetails.getFirstName());
            newEntity.setLastName(driverDetails.getLastName());
            newEntity.setAddress(driverDetails.getAddress());
            newEntity = repository.save(newEntity);             
        } else {
            repository.save(mapper.map(driverDetails,Driver.class));
             
        }

	}

	@Override
	public DriverDetails getDriver(int id) {
		Optional<Driver> employee = repository.findById(id);

		if (employee.isPresent()) {
			return mapper.map(employee.get(), DriverDetails.class);
		}
		return null;
	}

	@Override
	public void deleteDriver(int id) {
		Optional<Driver> employee = repository.findById(id);

		if (employee.isPresent()) {
			repository.deleteById(id);
		}
	}

	@Override
	public DriverDetails getBookedDrivers() {
		// TODO Auto-generated method stub
		return null;
	}

}
