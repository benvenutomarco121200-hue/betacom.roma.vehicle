package com.betacom.sb.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.CarReq;
import com.betacom.sb.dto.output.CarDTO;
import com.betacom.sb.dto.output.VehicleDTO;
import com.betacom.sb.enums.VehicleType;
import com.betacom.sb.mapping.CarMap;
import com.betacom.sb.mapping.VehicleMap;
import com.betacom.sb.models.Car;
import com.betacom.sb.models.Vehicle;
import com.betacom.sb.repositories.ICarRepository;
import com.betacom.sb.repositories.IVehicleRepository;
import com.betacom.sb.services.interfaces.ICarServices;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarImpl implements ICarServices{

	private final ICarRepository repoCar;
	private final IVehicleRepository repoVehicle;

	@Override
	public CarDTO getById(Long id) throws Exception {
		log.debug("get at id {}", id);
		Car car = repoCar.findById(id)
				.orElseThrow(() -> new Exception("car not found"));
	
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
			throw new Exception("license plate cannot be null");
		}
		if (repoCar.existsByLicensePlate(req.getLicensePlate())) {
			throw new Exception("license plate already present");
		}
		car.setLicensePlate(req.getLicensePlate());
		
		if (req.getDisplacementCc() == null) {
			throw new Exception("displacement cc cannot be null");
		}
		car.setDisplacementCc(req.getDisplacementCc());
		
		if (req.getDoorCount() == null){
			throw new Exception("door count cannot be null");
		}
		car.setDoorCount(req.getDoorCount());
		
		Vehicle vehicle = new Vehicle();
		
		
	    vehicle.setBrand(req.getBrand());
	    vehicle.setModel(req.getModel());
	    vehicle.setColor(req.getColor());
	    vehicle.setWheelCount(req.getWheelCount());
	    vehicle.setProductionYear(req.getProductionYear());
	    vehicle.setFuelType(req.getFuelType());
	    vehicle.setCategory(req.getCategory());
	    vehicle.setVehicleType(VehicleType.CAR);
	    
	    car.setVehicle(vehicle);
	    vehicle.setCar(car);
		
	    repoVehicle.save(vehicle);
		repoCar.save(car);
	}
	
	@Transactional
	@Override
	public void update(CarReq req) throws Exception {
	    log.debug("update {}", req);

	    if (req.getId() == null || req.getId() == 0) {
	        throw new Exception("Car ID is required for update");
	    }

	    Car car = repoCar.findById(req.getId())
	            .orElseThrow(() -> new Exception("Car not found with id: " + req.getId()));

	    if (!car.getLicensePlate().equals(req.getLicensePlate())) {
	        if (repoCar.existsByLicensePlate(req.getLicensePlate())) {
	            throw new Exception("The new license plate is already present on another car");
	        }
	        car.setLicensePlate(req.getLicensePlate());
	    }

	    car.setDisplacementCc(req.getDisplacementCc());
	    car.setDoorCount(req.getDoorCount());

	    Vehicle vehicle = car.getVehicle();
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

	    repoCar.save(car);
	}

	@Transactional
	@Override
	public void delete(Long id) throws Exception {
		log.debug("delete {}", id);
		Car car = repoCar.findById(id)
				.orElseThrow(() -> new Exception("id not valid"));
		
		repoCar.delete(car);
	}
}
