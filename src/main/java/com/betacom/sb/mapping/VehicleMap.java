package com.betacom.sb.mapping;

import java.util.List;

import com.betacom.sb.dto.output.VehicleDTO;
import com.betacom.sb.models.Vehicle;

public class VehicleMap {
	public static List<VehicleDTO> buildListVehicleDTO(List<Vehicle> lV){
		return lV.stream().map(v -> buildVehicleDTO(v)).toList();
	}
	
	
	public static VehicleDTO buildVehicleDTO(Vehicle vehicle) {
		return VehicleDTO.builder()
				.id(vehicle.getId())
				.vehicleType(vehicle.getVehicleType())
				.category(vehicle.getCategory())
				.wheelCount(vehicle.getWheelCount())
				.color(vehicle.getColor())
				.brand(vehicle.getBrand())
				.productionYear(vehicle.getProductionYear())
				.model(vehicle.getModel())
				.build();
	}
}
