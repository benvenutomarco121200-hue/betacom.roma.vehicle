package com.betacom.sb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.sb.models.FuelType;

public interface IFuelTypeRepository extends JpaRepository<FuelType, Integer>{
	Optional<FuelType> findByFuelIgnoreCase(String fuel);

}
