package com.betacom.sb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.sb.models.Vehicle;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {

}
