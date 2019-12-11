package com.demo.carbookingservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.carbookingservice.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

	@Query(value = "SELECT * FROM Car WHERE status = ?1", nativeQuery = true)
	List<Car> getCarsByStatus(String status);
	
	@Query(value = "SELECT * FROM Car WHERE driver_id is not null", nativeQuery = true)
	List<Car> getBookedCars();
	
	@Query(value = "SELECT * FROM car WHERE booking_start_time <= NOW() AND booking_end_time >= NOW()", nativeQuery = true)
	List<Car> getActiveBooking();
}