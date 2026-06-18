package com.betacom.sb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.sb.models.VehicleType;

public interface IVehicleTypeRepository extends JpaRepository<VehicleType, Integer>{
	Optional<VehicleType> findByVehicleIgnoreCase(String vehicle);

}
