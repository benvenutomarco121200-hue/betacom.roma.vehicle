package com.betacom.sb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.betacom.sb.models.Vehicle;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {

	List<Vehicle> searchByFilter(
			@Param("id") Long id,
			@Param("vehicleType") String vehicleType,
			@Param("category") String category,
			@Param("fuelType") String fuelType,
			@Param("color") String color,
			@Param("brand") String brand,
			@Param("model") String model,
			@Param("licensePlate") String licensePlate,
			@Param("doorCount") Integer doorCount
			);
}
