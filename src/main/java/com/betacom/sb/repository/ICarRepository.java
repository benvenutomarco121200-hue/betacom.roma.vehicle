package com.betacom.sb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.sb.models.Car;

public interface ICarRepository extends JpaRepository<Car, Integer>{

}
