package com.betacom.sb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.sb.models.FuelType;

@Repository
public interface IFuelTypeRepository extends JpaRepository<FuelType, Long>{


}
