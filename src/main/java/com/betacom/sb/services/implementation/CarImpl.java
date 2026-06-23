package com.betacom.sb.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.CarReq;
import com.betacom.sb.dto.output.CarDTO;
import com.betacom.sb.mapping.CarMap;
import com.betacom.sb.models.Car;
import com.betacom.sb.models.Vehicle;
import com.betacom.sb.repositories.ICarRepository;
import com.betacom.sb.repositories.IMotorcycleRepository;
import com.betacom.sb.repositories.IVehicleRepository;
import com.betacom.sb.services.interfaces.ICarServices;
import com.betacom.sb.services.interfaces.IVehicleServices;

import exceptions.BetacomRomaException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarImpl implements ICarServices {

	private final ICarRepository repoCar;
	private final IVehicleRepository repoVehicle;
	private final IMotorcycleRepository repoMoto;
	private final IVehicleServices servVehicle;

	@Override
	public CarDTO getById(Long id) throws Exception {
		log.debug("get at id {}", id);
		Car car = repoCar.findById(id)
				.orElseThrow(() -> new BetacomRomaException("car not found"));
	
		return CarMap.buildCarDTO(car);
	}

	@Override
	public List<CarDTO> list() throws Exception {
		log.debug("list");
		List<Car> cars = repoCar.findAll();
		return CarMap.buildListCarDTO(cars);
	}
	
	@Transactional
	@Override
	public void create(CarReq req) throws Exception {
		log.debug("create {}", req);
		Car car = new Car();

		if (req.getLicensePlate() == null) {
			throw new BetacomRomaException("license plate cannot be null");
		}
		if (repoCar.existsByLicensePlate(req.getLicensePlate()) || repoMoto.existsByLicensePlate(req.getLicensePlate())) {
			throw new BetacomRomaException("license plate already present");
		}
		
		Vehicle vehicle = repoVehicle.findById(servVehicle.create(req))
				.orElseThrow(() -> new BetacomRomaException("vehicle not create"));
		
		car.setLicensePlate(req.getLicensePlate().toUpperCase());
		
		car.setDisplacementCc(Optional.ofNullable(req.getDisplacementCc())
		        .orElseThrow(() -> new BetacomRomaException("displacement cc cannot be null")));
		
		car.setDoorCount(Optional.ofNullable(req.getDoorCount())
				.orElseThrow(() -> new BetacomRomaException("door count cannot be null")));
		

	    car.setVehicle(vehicle);
	    vehicle.setCar(car);
		
		repoCar.save(car);
	}
	
	@Transactional
	@Override
	public void update(CarReq req) throws Exception {
	    log.debug("update {}", req);

	    if (req.getId() == null || req.getId() == 0) {
	        throw new BetacomRomaException("Car ID is required for update");
	    }

	    Car car = repoCar.findById(req.getId())
	            .orElseThrow(() -> new BetacomRomaException("Car not found with id: " + req.getId()));

	    Vehicle vehicle = car.getVehicle();

	    if (vehicle == null) {
	        throw new BetacomRomaException("Vehicle linked to car not found");
	    }

	    if (req.getLicensePlate() != null && !req.getLicensePlate().equalsIgnoreCase(car.getLicensePlate())) {
	        if (repoCar.existsByLicensePlate(req.getLicensePlate()) || repoMoto.existsByLicensePlate(req.getLicensePlate())) {
	            throw new BetacomRomaException("The new license plate is already present on another vehicle");
	        }
	        car.setLicensePlate(req.getLicensePlate().toUpperCase());
	    }

	    Optional.ofNullable(req.getDisplacementCc()).ifPresent(car::setDisplacementCc);
	    Optional.ofNullable(req.getDoorCount()).ifPresent(car::setDoorCount);

	    //necessaria per aggiornare anche il veicolo
	    servVehicle.update(req, vehicle);

	    repoCar.save(car);
	}

	@Transactional
	@Override
	public void delete(Long id) throws Exception {
		log.debug("delete {}", id);
		Car car = repoCar.findById(id)
				.orElseThrow(() -> new BetacomRomaException("id not valid"));
		
		repoCar.delete(car);
	}
}
