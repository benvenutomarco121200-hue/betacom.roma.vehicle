package com.betacom.sb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.sb.models.Motorcycle;

@Repository
public interface IMotorcycleRepository extends JpaRepository<Motorcycle, Long> {

}
