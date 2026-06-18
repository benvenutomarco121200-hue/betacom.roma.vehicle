package com.betacom.sb.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.CarReq;
import com.betacom.sb.dto.output.CarDTO;
import com.betacom.sb.mapping.CarMap;
import com.betacom.sb.models.Car;
import com.betacom.sb.models.Category;
import com.betacom.sb.models.FuelType;
import com.betacom.sb.models.Vehicle;
import com.betacom.sb.models.VehicleType;
import com.betacom.sb.repositories.ICarRepository;
import com.betacom.sb.repositories.ICategoryRepository;
import com.betacom.sb.repositories.IFuelTypeRepository;
import com.betacom.sb.repositories.IMotorcycleRepository;
import com.betacom.sb.repositories.IVehicleRepository;
import com.betacom.sb.repositories.IVehicleTypeRepository;
import com.betacom.sb.services.interfaces.ICarServices;
import com.betacom.sb.utils.Utils;

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
	private final IFuelTypeRepository repoFuel;       
	private final ICategoryRepository repoCategory;   
	private final IVehicleTypeRepository repoVehicleType;

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
		car.setLicensePlate(req.getLicensePlate());
		
		if (req.getDisplacementCc() == null) {
			throw new BetacomRomaException("displacement cc cannot be null");
		}
		car.setDisplacementCc(req.getDisplacementCc());
		
		if (req.getDoorCount() == null){
			throw new BetacomRomaException("door count cannot be null");
		}
		car.setDoorCount(req.getDoorCount());
		
		if (req.getFuelType() == null) {
			throw new BetacomRomaException("Fuel type cannot be null");
		}
		FuelType fuelType = repoFuel.findByFuelIgnoreCase(req.getFuelType().trim())
				.orElseThrow(() -> new BetacomRomaException("The value '" + req.getFuelType() + "' is not a valid FuelType."));

		if (req.getCategory() == null) {
			throw new BetacomRomaException("Category cannot be null");
		}
		Category category = repoCategory.findByCategoryIgnoreCase(req.getCategory().trim())
				.orElseThrow(() -> new BetacomRomaException("The value '" + req.getCategory() + "' is not a valid Category."));
		
		VehicleType vehicleType = repoVehicleType.findByVehicleIgnoreCase("CAR")
	            .orElseThrow(() -> new BetacomRomaException("VehicleType 'CAR' not found in database. Make sure it is populated."));
		
		Vehicle vehicle = Utils.checkVehicleCreate(
				req.getBrand(), 
				req.getModel(), 
                req.getColor(), 
                req.getWheelCount(), 
                req.getProductionYear(), 
                fuelType, 
                category, 
                vehicleType
		);
	    
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
	        throw new BetacomRomaException("Car ID is required for update");
	    }

	    Car car = repoCar.findById(req.getId())
	            .orElseThrow(() -> new BetacomRomaException("Car not found with id: " + req.getId()));

	    if (req.getLicensePlate() != null && !req.getLicensePlate().equalsIgnoreCase(car.getLicensePlate())) {
			if (repoCar.existsByLicensePlate(req.getLicensePlate()) || repoMoto.existsByLicensePlate(req.getLicensePlate()))
				throw new BetacomRomaException("The new license plate is already present on another car");
			Optional.ofNullable(req.getLicensePlate()).ifPresent(car::setLicensePlate);
		}
	    
	    Optional.ofNullable(req.getDisplacementCc()).ifPresent(car::setDisplacementCc);
	    Optional.ofNullable(req.getDoorCount()).ifPresent(car::setDoorCount);

	    FuelType fuelType = null;
	    if (req.getFuelType() != null) {
	    	fuelType = repoFuel.findByFuelIgnoreCase(req.getFuelType().trim())
					.orElseThrow(() -> new BetacomRomaException("The value '" + req.getFuelType() + "' is not a valid FuelType."));
	    }

	    Category category = null;
	    if (req.getCategory() != null) {
	    	category = repoCategory.findByCategoryIgnoreCase(req.getCategory().trim())
					.orElseThrow(() -> new BetacomRomaException("The value '" + req.getCategory() + "' is not a valid Category."));
	    }

	    Utils.checkVehicleUpdate(
	    		car.getVehicle(), 
	    		req.getBrand(), 
	    		req.getModel(), 
	    		req.getColor(), 
	    		req.getWheelCount(), 
	    		req.getProductionYear(), 
	    		fuelType, 
	    		category
	    );

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
