package com.betacom.sb.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.MotorcycleReq;
import com.betacom.sb.dto.output.MotorcycleDTO;
import com.betacom.sb.enums.VehicleType;
import com.betacom.sb.mapping.MotorcycleMap;
import com.betacom.sb.models.Motorcycle;
import com.betacom.sb.models.Vehicle;
import com.betacom.sb.repositories.IMotorcycleRepository;
import com.betacom.sb.repositories.IVehicleRepository;
import com.betacom.sb.services.interfaces.IMotorcycleServices;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MotorcycleImpl implements IMotorcycleServices{

	private final IMotorcycleRepository repoMoto;
	private final IVehicleRepository repoVehicle;
	
	@Transactional
	@Override
	public void create(MotorcycleReq req) throws Exception {
		log.debug("create {}", req);
		Motorcycle moto = new Motorcycle();
		if (req.getLicensePlate() == null) {
			throw new Exception("license plate cannot be null");
		}
		if (repoMoto.existsByLicensePlate(req.getLicensePlate())) {
			throw new Exception("license plate already present");
		}
		moto.setLicensePlate(req.getLicensePlate());
		
		if (req.getDisplacementCc() == null) {
			throw new Exception("displacement cc cannot be null");
		}
		moto.setDisplacementCc(req.getDisplacementCc());
		
		Vehicle vehicle = new Vehicle();
		
	    vehicle.setBrand(req.getBrand());
	    vehicle.setModel(req.getModel());
	    vehicle.setColor(req.getColor());
	    vehicle.setWheelCount(req.getWheelCount());
	    vehicle.setProductionYear(req.getProductionYear());
	    vehicle.setFuelType(req.getFuelType());
	    vehicle.setCategory(req.getCategory());
	    vehicle.setVehicleType(VehicleType.CAR);
	    
	    moto.setVehicle(vehicle);
	    vehicle.setMotorcycle(moto);;
		
	    repoVehicle.save(vehicle);
		repoMoto.save(moto);
		
	}

	@Transactional
	@Override
	public void update(MotorcycleReq req) throws Exception {
		log.debug("update {}", req);

	    if (req.getId() == null || req.getId() == 0) {
	        throw new Exception("Car ID is required for update");
	    }

	    Motorcycle moto= repoMoto.findById(req.getId())
	            .orElseThrow(() -> new Exception("Car not found with id: " + req.getId()));

	    if (!moto.getLicensePlate().equals(req.getLicensePlate())) {
	        if (repoMoto.existsByLicensePlate(req.getLicensePlate())) {
	            throw new Exception("The new license plate is already present on another car");
	        }
	        moto.setLicensePlate(req.getLicensePlate());
	    }

	    moto.setDisplacementCc(req.getDisplacementCc());

	    Vehicle vehicle = moto.getVehicle();
	    if (vehicle == null) {
	        throw new Exception("Data integrity error: Linked vehicle not found for this car");
	    }

	    vehicle.setBrand(req.getBrand());
	    vehicle.setModel(req.getModel());
	    vehicle.setColor(req.getColor());
	    vehicle.setWheelCount(req.getWheelCount());
	    vehicle.setProductionYear(req.getProductionYear());
	    vehicle.setFuelType(req.getFuelType());
	    vehicle.setCategory(req.getCategory());

	    repoMoto.save(moto);
		
	}

	@Transactional
	@Override
	public void delete(Long id) throws Exception {
		log.debug("delete {}", id);
		Motorcycle moto = repoMoto.findById(id)
				.orElseThrow(() -> new Exception("id not valid"));
		
		repoMoto.delete(moto);
		
	}

	@Override
	public MotorcycleDTO getById(Long id) throws Exception {
		log.debug("get at id {}", id);
		Motorcycle moto = repoMoto.findById(id)
				.orElseThrow(() -> new Exception("car not found"));
	
		return MotorcycleMap.buildMotorcycleDTO(moto);
	}

	@Override
	public List<MotorcycleDTO> list() throws Exception {
		log.debug("list");
		List<Motorcycle> moto = repoMoto.findAll();
		return MotorcycleMap.buildListMotorcycleDTO(moto);
	}

	
}
