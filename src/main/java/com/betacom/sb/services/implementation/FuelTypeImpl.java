package com.betacom.sb.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.FuelTypeReq;
import com.betacom.sb.dto.output.FuelTypeDTO;
import com.betacom.sb.mapping.FuelTypeMap;
import com.betacom.sb.models.FuelType;
import com.betacom.sb.repositories.IFuelTypeRepository;
import com.betacom.sb.services.interfaces.IFuelTypeServices;

import exceptions.BetacomRomaException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FuelTypeImpl implements IFuelTypeServices {

	private final IFuelTypeRepository fuelRepo;
	
	@Transactional
	@Override
	public void create(FuelTypeReq req) throws BetacomRomaException {
		log.debug("create fuel type {}",req);
		
		FuelType tF = new FuelType();
		tF.setFuel(req.getFuelType());
		fuelRepo.save(tF);
	}

	@Transactional
	@Override
	public void delete(Long id) throws BetacomRomaException {
		log.debug("delete {}", id);
		FuelType tF = fuelRepo.findById(id)
				.orElseThrow(() -> new BetacomRomaException("key_ntfnd"));
		fuelRepo.delete(tF);
	}

	@Override
	public List<FuelTypeDTO> list() {
		log.debug("List");
		List<FuelType> lF = fuelRepo.findAll();
		
		return FuelTypeMap.buildFuelTypeDTOList(lF);
	}

	
}
