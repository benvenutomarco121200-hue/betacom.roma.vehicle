package com.betacom.sb.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.MotorcycleReq;
import com.betacom.sb.dto.output.MotorcycleDTO;
import com.betacom.sb.mapping.MotorcycleMap;
import com.betacom.sb.models.Motorcycle;
import com.betacom.sb.models.Vehicle;
import com.betacom.sb.repositories.ICarRepository;
import com.betacom.sb.repositories.IMotorcycleRepository;
import com.betacom.sb.repositories.IVehicleRepository;
import com.betacom.sb.services.interfaces.IMotorcycleServices;
import com.betacom.sb.services.interfaces.IVehicleServices;

import exceptions.BetacomRomaException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MotorcycleImpl implements IMotorcycleServices {

	private final IMotorcycleRepository repoMoto;
	private final IVehicleRepository repoVehicle;
	private final ICarRepository repoCar;
	private final IVehicleServices servVehicle;
	
	@Transactional
	@Override
	public void create(MotorcycleReq req) throws Exception {
		log.debug("create {}", req);
		Motorcycle moto = new Motorcycle();
		Vehicle vehicle = repoVehicle.findById(servVehicle.create(req))
				.orElseThrow(() -> new BetacomRomaException("vehicle not create"));
		
		if (req.getLicensePlate() == null) {
			throw new BetacomRomaException("license plate cannot be null");
		}
		if (repoCar.existsByLicensePlate(req.getLicensePlate()) || repoMoto.existsByLicensePlate(req.getLicensePlate())) {
			throw new BetacomRomaException("license plate already present");
		}
		moto.setLicensePlate(req.getLicensePlate().toUpperCase());
		
		moto.setDisplacementCc(Optional.ofNullable(req.getDisplacementCc())
		        .orElseThrow(() -> new BetacomRomaException("displacement cc cannot be null")));

		moto.setVehicle(vehicle);
	    vehicle.setMotorcycle(moto);
		
	    repoVehicle.save(vehicle);
		repoMoto.save(moto);
	}

	@Transactional
	@Override
	public void update(MotorcycleReq req) throws Exception {
		log.debug("update {}", req);

	    if (req.getId() == null || req.getId() == 0) {
	        throw new BetacomRomaException("Motorcycle ID is required for update");
	    }

	    Motorcycle moto = repoMoto.findById(req.getId())
	            .orElseThrow(() -> new BetacomRomaException("Motorcycle not found with id: " + req.getId()));

	    Vehicle vehicle = moto.getVehicle();

	    if (vehicle == null) {
	    	throw new BetacomRomaException("Vehicle linked to motorcycle not found");
	    }

	    if (req.getLicensePlate() != null && !req.getLicensePlate().equalsIgnoreCase(moto.getLicensePlate())) {
	        if (repoCar.existsByLicensePlate(req.getLicensePlate()) || repoMoto.existsByLicensePlate(req.getLicensePlate())) {
	            throw new BetacomRomaException("The new license plate is already present on another vehicle");
	        }
	        moto.setLicensePlate(req.getLicensePlate().toUpperCase());
	    }

	    Optional.ofNullable(req.getDisplacementCc()).ifPresent(moto::setDisplacementCc);

	    //necessaria per aggiornare anche il veicolo
	    servVehicle.update(req, vehicle);

	    repoMoto.save(moto);
	}

	@Transactional
	@Override
	public void delete(Long id) throws Exception {
		log.debug("delete {}", id);
		Motorcycle moto = repoMoto.findById(id)
				.orElseThrow(() -> new BetacomRomaException("id not valid"));
		
		repoMoto.delete(moto);
	}

	@Override
	public MotorcycleDTO getById(Long id) throws Exception {
		log.debug("get at id {}", id);
		Motorcycle moto = repoMoto.findById(id)
				.orElseThrow(() -> new BetacomRomaException("motorcycle not found"));
	
		return MotorcycleMap.buildMotorcycleDTO(moto);
	}

	@Override
	public List<MotorcycleDTO> list() throws Exception {
		log.debug("list");
		List<Motorcycle> moto = repoMoto.findAll();
		return MotorcycleMap.buildListMotorcycleDTO(moto);
	}
}
