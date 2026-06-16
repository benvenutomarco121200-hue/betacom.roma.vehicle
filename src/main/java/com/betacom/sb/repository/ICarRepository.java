package com.betacom.sb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.sb.models.Car;

@Repository
public interface ICarRepository extends JpaRepository<Car, Long>{

}
