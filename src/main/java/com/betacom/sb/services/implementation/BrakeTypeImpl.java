package com.betacom.sb.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.BrakeTypeReq;
import com.betacom.sb.dto.output.BrakeTypeDTO;
import com.betacom.sb.mapping.BrakeTypeMap;
import com.betacom.sb.models.BrakeType;
import com.betacom.sb.repositories.IBrakeTypeRepository;
import com.betacom.sb.services.interfaces.IBrakeTypeServices;

import exceptions.BetacomRomaException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrakeTypeImpl implements IBrakeTypeServices {

	private final IBrakeTypeRepository brakeRepo;

	@Override
	public void create(BrakeTypeReq req) throws BetacomRomaException {
		log.debug("create brake type {}",req);
		
		BrakeType tB = new BrakeType();
		tB.setBrakeType(req.getBrakeType());
		brakeRepo.save(tB);
		
	}

	@Override
	public void delete(Long id) throws BetacomRomaException {
		log.debug("delete {}", id);
		BrakeType tB = brakeRepo.findById(id)
				.orElseThrow(() -> new BetacomRomaException("key_ntfnd"));
		brakeRepo.delete(tB);
		
	}

	@Override
	public List<BrakeTypeDTO> list() {
		log.debug("List");
		List<BrakeType> lB = brakeRepo.findAll();
		
		return BrakeTypeMap.buildBrakeTypeDTOList(lB);
	}
	
	
}
