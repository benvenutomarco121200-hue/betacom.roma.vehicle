package com.betacom.sb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.sb.models.Bicycle;

@Repository
public interface IBicycleRepository extends JpaRepository<Bicycle, Long> {

}
