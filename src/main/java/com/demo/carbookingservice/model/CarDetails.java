package com.demo.carbookingservice.model;

import java.util.Date;

import com.demo.carbookingservice.entity.Driver;

public class CarDetails {

	private int id;
	private String vin;
	private String status;
	private Date bookingStartTime;
	private Date bookingEndTime;
	private Driver driver;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBookingStartTime() {
		return bookingStartTime;
	}

	public void setBookingStartTime(Date bookingStartTime) {
		this.bookingStartTime = bookingStartTime;
	}

	public Date getBookingEndTime() {
		return bookingEndTime;
	}

	public void setBookingEndTime(Date bookingEndTime) {
		this.bookingEndTime = bookingEndTime;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	@Override
	public String toString() {
		return "CarDetails [id=" + id + ", vin=" + vin + ", status=" + status + ", bookingStartTime=" + bookingStartTime
				+ ", bookingEndTime=" + bookingEndTime + ", driver=" + driver + "]";
	}

}
