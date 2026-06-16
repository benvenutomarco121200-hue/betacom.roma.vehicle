package com.betacom.sb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.sb.models.Motorcycle;

public interface IMotorcycleRepository extends JpaRepository<Motorcycle, Integer> {

}
