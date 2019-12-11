package com.demo.carbookingservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.carbookingservice.entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

}