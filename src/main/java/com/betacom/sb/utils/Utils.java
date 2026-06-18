package com.betacom.sb.utils;

import java.util.Optional;

import com.betacom.sb.dto.input.BicycleReq;
import com.betacom.sb.dto.input.CarReq;
import com.betacom.sb.dto.input.MotorcycleReq;
import com.betacom.sb.models.Bicycle;
import com.betacom.sb.models.Car;
import com.betacom.sb.models.Category;
import com.betacom.sb.models.FuelType;
import com.betacom.sb.models.Motorcycle;
import com.betacom.sb.models.Vehicle;
import com.betacom.sb.models.VehicleType;

import exceptions.BetacomRomaException;

public class Utils {

	public static Vehicle checkVehicleCreate(
			String brand, String model, String color, Integer wheelCount, Integer productionYear, 
			FuelType fuelType, Category category, VehicleType vehicleType) throws BetacomRomaException {
			
	    Vehicle vehicle = new Vehicle();
	    
	    vehicle.setBrand(Optional.ofNullable(brand).orElseThrow(() -> new BetacomRomaException("Brand cannot be null")));
	    vehicle.setModel(Optional.ofNullable(model).orElseThrow(() -> new BetacomRomaException("Model cannot be null")));
	    vehicle.setColor(Optional.ofNullable(color).orElseThrow(() -> new BetacomRomaException("Color cannot be null")));
	    vehicle.setWheelCount(Optional.ofNullable(wheelCount).orElseThrow(() -> new BetacomRomaException("Wheel count cannot be null")));
	    vehicle.setProductionYear(Optional.ofNullable(productionYear).orElseThrow(() -> new BetacomRomaException("Production year cannot be null")));

	    if (fuelType == null) throw new BetacomRomaException("Fuel type cannot be null");
	    if (category == null) throw new BetacomRomaException("Category cannot be null");
	    if (vehicleType == null) throw new BetacomRomaException("Vehicle type cannot be null");
	    
	    vehicle.setFuelType(fuelType);
	    vehicle.setCategory(category);
	    vehicle.setVehicleType(vehicleType); 
	    
	    return vehicle;
	}

	
	public static void checkVehicleUpdate(
			Vehicle vehicle, String brand, String model, String color, Integer wheelCount, Integer productionYear, 
			FuelType fuelType, Category category) {
			
	    if (vehicle == null) {
	        throw new BetacomRomaException("Data integrity error: Vehicle entity is required for update");
	    }

	    Optional.ofNullable(brand).ifPresent(vehicle::setBrand);
	    Optional.ofNullable(model).ifPresent(vehicle::setModel);
	    Optional.ofNullable(color).ifPresent(vehicle::setColor);
	    Optional.ofNullable(wheelCount).ifPresent(vehicle::setWheelCount);
	    Optional.ofNullable(productionYear).ifPresent(vehicle::setProductionYear);

	    if (fuelType != null) vehicle.setFuelType(fuelType);
	    if (category != null) vehicle.setCategory(category);
	}
}