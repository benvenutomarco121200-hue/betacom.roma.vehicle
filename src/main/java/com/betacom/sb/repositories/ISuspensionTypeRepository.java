package com.betacom.sb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.sb.models.SuspensionType;

public interface ISuspensionTypeRepository extends JpaRepository<SuspensionType, Integer>{
	Optional<SuspensionType> findBySuspensionIgnoreCase(String suspension);

}
