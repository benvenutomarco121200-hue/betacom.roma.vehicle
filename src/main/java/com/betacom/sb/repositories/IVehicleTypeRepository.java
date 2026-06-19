package com.betacom.sb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.sb.models.VehicleType;

@Repository
public interface IVehicleTypeRepository extends JpaRepository<VehicleType, Long>{
	Optional<VehicleType> findByVehicleIgnoreCase(String vehicle);
	Boolean existsByType(String vehicleType);

}
