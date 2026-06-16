package com.betacom.sb.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.output.VehicleDTO;
import com.betacom.sb.mapping.VehicleMap;
import com.betacom.sb.models.Vehicle;
import com.betacom.sb.repositories.IVehicleRepository;
import com.betacom.sb.services.interfaces.IVehicleServices;

import exceptions.BetacomRomaException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleImpl implements IVehicleServices{

	private final IVehicleRepository repoVehicle;
	
	@Override
	public VehicleDTO getById(Long id) throws Exception {
		log.debug("get at id {}", id);
		Vehicle vehicle = repoVehicle.findById(id)
				.orElseThrow(() -> new BetacomRomaException("vehicle not found"));
	
		return VehicleMap.buildVehicleDTO(vehicle);
	}

	@Override
	public List<VehicleDTO> list() throws Exception {
		log.debug("list");
		List<Vehicle> lV = repoVehicle.findAll();
		return VehicleMap.buildListVehicleDTO(lV);
	}
	
}
