package com.betacom.sb.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.MotorcycleReq;
import com.betacom.sb.dto.output.MotorcycleDTO;
import com.betacom.sb.mapping.MotorcycleMap;
import com.betacom.sb.models.Category;
import com.betacom.sb.models.FuelType;
import com.betacom.sb.models.Motorcycle;
import com.betacom.sb.models.Vehicle;
import com.betacom.sb.models.VehicleType;
import com.betacom.sb.repositories.ICarRepository;
import com.betacom.sb.repositories.ICategoryRepository;
import com.betacom.sb.repositories.IFuelTypeRepository;
import com.betacom.sb.repositories.IMotorcycleRepository;
import com.betacom.sb.repositories.IVehicleRepository;
import com.betacom.sb.repositories.IVehicleTypeRepository;
import com.betacom.sb.services.interfaces.IMotorcycleServices;
import com.betacom.sb.utils.Utils;

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
	private final IFuelTypeRepository repoFuel;
	private final ICategoryRepository repoCategory;
	private final IVehicleTypeRepository repoVehicleType;
	
	@Transactional
	@Override
	public void create(MotorcycleReq req) throws Exception {
		log.debug("create {}", req);
		Motorcycle moto = new Motorcycle();
		if (req.getLicensePlate() == null) {
			throw new BetacomRomaException("license plate cannot be null");
		}
		if (repoMoto.existsByLicensePlate(req.getLicensePlate()) || repoCar.existsByLicensePlate(req.getLicensePlate())) {
			throw new BetacomRomaException("license plate already present");
		}
		moto.setLicensePlate(req.getLicensePlate());
		
		if (req.getDisplacementCc() == null) {
			throw new BetacomRomaException("displacement cc cannot be null");
		}
		moto.setDisplacementCc(req.getDisplacementCc());
		
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
		
		VehicleType vehicleType = repoVehicleType.findByVehicleIgnoreCase("MOTORCYCLE")
	            .orElseThrow(() -> new BetacomRomaException("VehicleType 'MOTORCYCLE' not found in database."));
		
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

	    if (req.getLicensePlate() != null && !req.getLicensePlate().equalsIgnoreCase(moto.getLicensePlate())) {
			if (repoCar.existsByLicensePlate(req.getLicensePlate()) || repoMoto.existsByLicensePlate(req.getLicensePlate()))
				throw new BetacomRomaException("The new license plate is already present on another motorcycle");
			Optional.ofNullable(req.getLicensePlate()).ifPresent(moto::setLicensePlate);
		}

	    Optional.ofNullable(req.getDisplacementCc()).ifPresent(moto::setDisplacementCc);
	    
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
	    		moto.getVehicle(), 
	    		req.getBrand(), 
	    		req.getModel(), 
	    		req.getColor(), 
	    		req.getWheelCount(), 
	    		req.getProductionYear(), 
	    		fuelType, 
	    		category
	    );

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
