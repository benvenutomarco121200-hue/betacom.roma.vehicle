package com.betacom.sb.mapping;

import java.util.List;

import com.betacom.sb.dto.output.VehicleDTO;
import com.betacom.sb.models.Vehicle;

public class VehicleMap {

	public static List<VehicleDTO> buildListVehicleDTO(List<Vehicle> lV){
		return lV.stream().map(v -> buildVehicleDTO(v)).toList();
	}
	
	public static VehicleDTO buildVehicleDTO(Vehicle vehicle) {
		if (vehicle == null) {
			return null;
		}

		return VehicleDTO.builder()
				.id(vehicle.getId())
				.wheelCount(vehicle.getWheelCount())
				.color(vehicle.getColor())
				.brand(vehicle.getBrand())
				.productionYear(vehicle.getProductionYear())
				.model(vehicle.getModel())
				.vehicleType(VehicleTypeMap.buildVehicleTypeDTO(vehicle.getVehicleType()))
				.category(CategoryMap.buildCategoryDTO(vehicle.getCategory()))
				.fuelType(FuelTypeMap.buildFuelTypeDTO(vehicle.getFuelType()))		
				
				.car(vehicle.getCar() == null ? null : CarMap.buildCarDTO(vehicle.getCar()))
				.motorcycle(vehicle.getMotorcycle() == null ? null : MotorcycleMap.buildMotorcycleDTO(vehicle.getMotorcycle()))
				.bicycle(vehicle.getBicycle() == null ? null : BicycleMap.buildBicycleDTO(vehicle.getBicycle()))

				.build();
	}
}


