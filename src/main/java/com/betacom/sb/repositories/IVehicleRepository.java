package com.betacom.sb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.betacom.sb.models.Vehicle;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {
	
	@Query(name = "vehicle.selectByColor")
	public List<Vehicle> selectByColor(@Param("color") String color);
	
}
