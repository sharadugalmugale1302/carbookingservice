package com.demo.carbookingservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Car implements Serializable {

	public Integer getId() {
		return id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String vin;
	private String status;
	private Date bookingStartTime;
	private Date bookingEndTime;

	@OneToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "driver_id", nullable = true)
	private Driver driver;

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

	public void setId(Integer id) {
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

}
