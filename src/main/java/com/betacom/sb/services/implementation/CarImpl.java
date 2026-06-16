package com.betacom.sb.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.CarReq;
import com.betacom.sb.dto.output.CarDTO;
import com.betacom.sb.mapping.CarMap;
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
		
		
		
//		repoVehicle.save
//		Vehicle vehicle = repoVehicle.findById(req.getVehicleId())
//				.orElseThrow(() -> new Exception("vehicle not found"));
		
		
		repoCar.save(car);
	}
	
	@Transactional
	@Override
	public void update(CarReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
