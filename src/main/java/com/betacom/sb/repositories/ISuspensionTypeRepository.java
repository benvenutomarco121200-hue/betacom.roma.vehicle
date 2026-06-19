package com.betacom.sb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.sb.models.SuspensionType;

@Repository
public interface ISuspensionTypeRepository extends JpaRepository<SuspensionType, Long>{
	Optional<SuspensionType> findBySuspensionIgnoreCase(String suspension);
	Boolean existBySuspension(String suspensionType);

}
