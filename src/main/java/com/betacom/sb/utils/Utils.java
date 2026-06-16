package com.betacom.sb.utils;

import java.util.Optional;

import com.betacom.sb.dto.input.BicycleReq;
import com.betacom.sb.dto.input.CarReq;
import com.betacom.sb.dto.input.MotorcycleReq;
import com.betacom.sb.enums.VehicleType;
import com.betacom.sb.models.Bicycle;
import com.betacom.sb.models.Car;
import com.betacom.sb.models.Motorcycle;
import com.betacom.sb.models.Vehicle;

import exceptions.BetacomRomaException;

public class Utils {
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
	
	public static Vehicle checkVehicleCarCreate(CarReq req) throws BetacomRomaException {
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

		vehicle.setFuelType(Optional.ofNullable(req.getFuelType())
		        .orElseThrow(() -> new BetacomRomaException("Fuel type cannot be null")));

		vehicle.setCategory(Optional.ofNullable(req.getCategory())
		        .orElseThrow(() -> new BetacomRomaException("Category cannot be null")));

		vehicle.setVehicleType(VehicleType.CAR);
		
		return vehicle;
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
	
	public static Vehicle checkVehicleMotorcycleCreate(MotorcycleReq req) throws BetacomRomaException {
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

		vehicle.setFuelType(Optional.ofNullable(req.getFuelType())
		        .orElseThrow(() -> new BetacomRomaException("Fuel type cannot be null")));

		vehicle.setCategory(Optional.ofNullable(req.getCategory())
		        .orElseThrow(() -> new BetacomRomaException("Category cannot be null")));

		vehicle.setVehicleType(VehicleType.MOTORCYCLE);
		
		return vehicle;
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
	
	public static Vehicle checkVehicleBicycleCreate(BicycleReq req) throws BetacomRomaException {
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

		vehicle.setFuelType(Optional.ofNullable(req.getFuelType())
		        .orElseThrow(() -> new BetacomRomaException("Fuel type cannot be null")));

		vehicle.setCategory(Optional.ofNullable(req.getCategory())
		        .orElseThrow(() -> new BetacomRomaException("Category cannot be null")));

		vehicle.setVehicleType(VehicleType.BICYCLE);
		
		return vehicle;
	}

	
	
}
