package com.betacom.sb.utils;

import java.util.Optional;

import com.betacom.sb.dto.input.BicycleReq;
import com.betacom.sb.dto.input.CarReq;
import com.betacom.sb.dto.input.MotorcycleReq;
import com.betacom.sb.dto.input.VehicleReq;
import com.betacom.sb.models.Bicycle;
import com.betacom.sb.models.Car;
import com.betacom.sb.models.Motorcycle;
import com.betacom.sb.models.Vehicle;

import exceptions.BetacomRomaException;

public class Utils {	
	public static Vehicle checkVehicleCreate(VehicleReq req) throws BetacomRomaException {
		Vehicle vehicle = new Vehicle();
		
		vehicle.setBrand(req.getBrand());
		vehicle.setModel(req.getModel());
		vehicle.setColor(req.getColor());
		vehicle.setWheelCount(req.getWheelCount());
		vehicle.setProductionYear(req.getProductionYear());
		vehicle.setFuelType(req.getFuelType());
		vehicle.setCategory(req.getCategory());
		      
		return vehicle;
	}
	
	public static void checkVehicleCarUpdate(CarReq req, Car car) {
	    Vehicle vehicle = car.getVehicle();
	    if (vehicle == null) {
	        throw new BetacomRomaException("Data integrity error: Linked vehicle not found for this car");
	    }

	    Optional.ofNullable(req.getBrand()).ifPresent(vehicle::setBrand);

	    Optional.ofNullable(req.getModel()).ifPresent(vehicle::setModel);

	    Optional.ofNullable(req.getColor()).ifPresent(vehicle::setColor);

	    Optional.ofNullable(req.getWheelCount()).ifPresent(vehicle::setWheelCount);

	    Optional.ofNullable(req.getProductionYear()).ifPresent(vehicle::setProductionYear);

	    Optional.ofNullable(req.getFuelType()).ifPresent(vehicle::setFuelType);

	    Optional.ofNullable(req.getCategory()).ifPresent(vehicle::setCategory);
	}
	
	public static void checkVehicleMotorcycleUpdate(MotorcycleReq req, Motorcycle moto) {
	    Vehicle vehicle = moto.getVehicle();
	    if (vehicle == null) {
	        throw new BetacomRomaException("Data integrity error: Linked vehicle not found for this car");
	    }

	    Optional.ofNullable(req.getBrand()).ifPresent(vehicle::setBrand);

	    Optional.ofNullable(req.getModel()).ifPresent(vehicle::setModel);

	    Optional.ofNullable(req.getColor()).ifPresent(vehicle::setColor);

	    Optional.ofNullable(req.getWheelCount()).ifPresent(vehicle::setWheelCount);

	    Optional.ofNullable(req.getProductionYear()).ifPresent(vehicle::setProductionYear);

	    Optional.ofNullable(req.getFuelType()).ifPresent(vehicle::setFuelType);

	    Optional.ofNullable(req.getCategory()).ifPresent(vehicle::setCategory);
	}
	
	public static void checkVehicleBicycleUpdate(BicycleReq req, Bicycle bicycle) {
	    Vehicle vehicle = bicycle.getVehicle();
	    if (vehicle == null) {
	        throw new BetacomRomaException("Data integrity error: Linked vehicle not found for this car");
	    }

	    Optional.ofNullable(req.getBrand()).ifPresent(vehicle::setBrand);

	    Optional.ofNullable(req.getModel()).ifPresent(vehicle::setModel);

	    Optional.ofNullable(req.getColor()).ifPresent(vehicle::setColor);

	    Optional.ofNullable(req.getWheelCount()).ifPresent(vehicle::setWheelCount);

	    Optional.ofNullable(req.getProductionYear()).ifPresent(vehicle::setProductionYear);

	    Optional.ofNullable(req.getFuelType()).ifPresent(vehicle::setFuelType);

	    Optional.ofNullable(req.getCategory()).ifPresent(vehicle::setCategory);
	}
	
}