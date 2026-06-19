package com.betacom.sb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.sb.models.SuspensionType;

@Repository
public interface ISuspensionTypeRepository extends JpaRepository<SuspensionType, Long>{

}
