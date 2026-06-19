package com.betacom.sb.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.VehicleReq;
import com.betacom.sb.dto.output.VehicleDTO;
import com.betacom.sb.mapping.VehicleMap;
import com.betacom.sb.models.Category;
import com.betacom.sb.models.FuelType;
import com.betacom.sb.models.Vehicle;
import com.betacom.sb.models.VehicleType;
import com.betacom.sb.repositories.ICategoryRepository;
import com.betacom.sb.repositories.IFuelTypeRepository;
import com.betacom.sb.repositories.IVehicleRepository;
import com.betacom.sb.repositories.IVehicleTypeRepository;
import com.betacom.sb.services.interfaces.IVehicleServices;

import exceptions.BetacomRomaException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleImpl implements IVehicleServices{

	private final IVehicleRepository repoVehicle;
	private final IFuelTypeRepository repoFuel;
	private final ICategoryRepository repoCategory;
	private final IVehicleTypeRepository repoType;
	
	@Override
	public VehicleDTO getById(Long id) throws Exception {
		log.debug("get at id {}", id);
		Vehicle vehicle = repoVehicle.findById(id)
				.orElseThrow(() -> new BetacomRomaException("vehicle not found"));
	
		return VehicleMap.buildVehicleDTO(vehicle);
	}

	@Override
	public List<VehicleDTO> listAll() throws Exception {
		log.debug("list");
		List<Vehicle> lV = repoVehicle.findAll();
		return VehicleMap.buildListVehicleDTO(lV);
	}
	
	@Transactional
	@Override
	public Long create(VehicleReq req) {
		
		Vehicle vehicle = new Vehicle();
		
		vehicle.setBrand(Optional.ofNullable(req.getBrand())
		        .orElseThrow(() -> new BetacomRomaException("Brand cannot be null")));

		vehicle.setModel(Optional.ofNullable(req.getModel())
		        .orElseThrow(() -> new BetacomRomaException("Model cannot be null")));

		vehicle.setColor(Optional.ofNullable(req.getColor())
		        .orElseThrow(() -> new BetacomRomaException("Color cannot be null")));

		vehicle.setWheelCount(Optional.ofNullable(req.getWheelCount())
		        .orElseThrow(() -> new BetacomRomaException("Wheel count cannot be null")));

		vehicle.setProductionYear(Optional.ofNullable(req.getProductionYear())
		        .orElseThrow(() -> new BetacomRomaException("Production year cannot be null")));
		
		FuelType fuelType = repoFuel.findById(
		        Optional.ofNullable(req.getFuelType())
		                .orElseThrow(() -> new BetacomRomaException("Fuel type cannot be null"))
		                .getId())
		        .orElseThrow(() -> new BetacomRomaException("fuel_type_invalid"));
		vehicle.setFuelType(fuelType);

		VehicleType vehicleType = repoType.findById(
		        Optional.ofNullable(req.getVehicleType())
		                .orElseThrow(() -> new BetacomRomaException("Vehicle type cannot be null"))
		                .getId())
		        .orElseThrow(() -> new BetacomRomaException("vehicle_type_invalid"));
		vehicle.setVehicleType(vehicleType);

		Category category = repoCategory.findById(
		        Optional.ofNullable(req.getCategory())
		                .orElseThrow(() -> new BetacomRomaException("Category cannot be null"))
		                .getId())
		        .orElseThrow(() -> new BetacomRomaException("category_invalid"));

		vehicle.setCategory(category);
		
		return repoVehicle.save(vehicle).getId();
	}
	
	@Transactional
	@Override
	public void update(VehicleReq req, Vehicle vehicle) throws Exception {

	    Optional.ofNullable(req.getBrand()).ifPresent(vehicle::setBrand);
	    Optional.ofNullable(req.getModel()).ifPresent(vehicle::setModel);
	    Optional.ofNullable(req.getColor()).ifPresent(vehicle::setColor);
	    Optional.ofNullable(req.getWheelCount()).ifPresent(vehicle::setWheelCount);
	    Optional.ofNullable(req.getProductionYear()).ifPresent(vehicle::setProductionYear);

	    if (req.getFuelType() != null) {
	        FuelType fuelType = repoFuel.findById(req.getFuelType().getId())
	                .orElseThrow(() -> new BetacomRomaException("fuel_type_invalid"));
	        vehicle.setFuelType(fuelType);
	    }

	    if (req.getVehicleType() != null) {
	        VehicleType vehicleType = repoType.findById(req.getVehicleType().getId())
	                .orElseThrow(() -> new BetacomRomaException("vehicle_type_invalid"));
	        vehicle.setVehicleType(vehicleType);
	    }

	    if (req.getCategory() != null) {
	        Category category = repoCategory.findById(req.getCategory().getId())
	                .orElseThrow(() -> new BetacomRomaException("category_invalid"));
	        vehicle.setCategory(category);
	    }

	    repoVehicle.save(vehicle);
	}
	
	@Override
	public List<VehicleDTO> find(Long id, String vehicleType, String category, String fuelType, String color,
			String brand, String model, String licensePlate, Integer doorCount) {
		log.debug("find {}/{}/{}/{}/{}/{}/{}/{}", id, vehicleType, category, fuelType, color, brand, model, licensePlate, doorCount);
		
		List<Vehicle> lV = repoVehicle.searchByFilter(id, vehicleType, category, fuelType, color, brand, model, licensePlate, doorCount);
		
		log.debug("Size:" + lV.size());
		
		
		return VehicleMap.buildListVehicleDTO(lV);
	}	
}
