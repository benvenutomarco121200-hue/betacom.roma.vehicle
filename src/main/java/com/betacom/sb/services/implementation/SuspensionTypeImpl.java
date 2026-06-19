package com.betacom.sb.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.SuspensionTypeReq;
import com.betacom.sb.dto.output.SuspensionTypeDTO;
import com.betacom.sb.mapping.SuspensionTypeMap;
import com.betacom.sb.models.SuspensionType;
import com.betacom.sb.repositories.ISuspensionTypeRepository;
import com.betacom.sb.services.interfaces.ISuspensionTypeServices;

import exceptions.BetacomRomaException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SuspensionTypeImpl implements ISuspensionTypeServices {

	private final ISuspensionTypeRepository suspensionRepo;

	@Transactional
	@Override
	public void create(SuspensionTypeReq req) throws BetacomRomaException {
		log.debug("create suspension type {}",req);
		if (suspensionRepo.existBySuspension(req.getSuspensionType()))
			throw new BetacomRomaException("Suspension_type_exits");
		
		SuspensionType tS = new SuspensionType();
		tS.setSuspensionType(req.getSuspensionType());
		suspensionRepo.save(tS);
		
	}

	@Transactional
	@Override
	public void delete(Long id) throws BetacomRomaException {
		log.debug("delete {}", id);
		SuspensionType tS = suspensionRepo.findById(id)
				.orElseThrow(() -> new BetacomRomaException("key_ntfnd"));
		suspensionRepo.delete(tS);
	}

	@Override
	public List<SuspensionTypeDTO> list() {
		log.debug("List");
		List<SuspensionType> lS = suspensionRepo.findAll();
		
		return SuspensionTypeMap.buildSuspensionTypeDTOList(lS);
	}
	
	
}
