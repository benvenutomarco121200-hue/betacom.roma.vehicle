package com.betacom.sb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.sb.models.BrakeType;

@Repository
public interface IBrakeTypeRepository extends JpaRepository<BrakeType, Long>{


}
