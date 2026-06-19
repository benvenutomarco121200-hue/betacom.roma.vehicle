package com.betacom.sb.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.VehicleTypeReq;
import com.betacom.sb.dto.output.VehicleTypeDTO;
import com.betacom.sb.mapping.VehicleTypeMap;
import com.betacom.sb.models.VehicleType;
import com.betacom.sb.repositories.IVehicleTypeRepository;
import com.betacom.sb.services.interfaces.IVehicleTypeServices;

import exceptions.BetacomRomaException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleTypeImpl implements IVehicleTypeServices {

	private final IVehicleTypeRepository vehicleTypeRepo;
	
	@Transactional
	@Override
	public void create(VehicleTypeReq req) throws BetacomRomaException {
		log.debug("create vehicle type {}",req);
		if (vehicleTypeRepo.existsByType(req.getVehicleType()))
			throw new BetacomRomaException("Vehcile_type_exits");
		
		VehicleType tV = new VehicleType();
		tV.setVehicleType(req.getVehicleType());
		vehicleTypeRepo.save(tV);
	}

	@Transactional
	@Override
	public void delete(Long id) throws BetacomRomaException {
		log.debug("delete {}", id);
		VehicleType tV = vehicleTypeRepo.findById(id)
				.orElseThrow(() -> new BetacomRomaException("key_ntfnd"));
		vehicleTypeRepo.delete(tV);
		
	}

	@Override
	public List<VehicleTypeDTO> list() {
		log.debug("List");
		List<VehicleType> lC = vehicleTypeRepo.findAll();
		
		return VehicleTypeMap.buildVehicleTypeDTOList(lC);
	}

	
}
