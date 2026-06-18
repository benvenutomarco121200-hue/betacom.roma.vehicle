package com.betacom.sb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.sb.models.BrakeType;

public interface IBrakeTypeRepository extends JpaRepository<BrakeType, Integer>{
	Optional<BrakeType> findByBrakeIgnoreCase(String brake);

}
