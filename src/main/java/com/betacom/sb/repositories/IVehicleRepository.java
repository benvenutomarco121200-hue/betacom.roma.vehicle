package com.betacom.sb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.betacom.sb.models.Vehicle;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {

	@Query("""
		    select v from Vehicle v
		        join v.vehicleType vt
		        join v.category cat
		        join v.fuelType fuel
		        left join v.car car
		        left join v.motorcycle moto
		        left join v.bicycle bici
		    where (:id is null or v.id = :id)
		        and (:vehicleType is null or vt.vehicleType = :vehicleType)
		        and (:category is null or cat.category = :category)
		        and (:fuelType is null or fuel.fuel = :fuelType)
		        and (:color is null or v.color = :color)
		        and (:brand is null or v.brand = :brand)
		        and (:model is null or v.model = :model)
		        and (:licensePlate is null or car.licensePlate = :licensePlate or moto.licensePlate = :licensePlate)
		        and (:doorCount is null or car.doorCount = :doorCount)
		""")
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
