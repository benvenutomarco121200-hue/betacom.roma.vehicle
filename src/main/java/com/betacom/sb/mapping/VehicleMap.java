package com.betacom.sb.mapping;

import java.util.List;

import com.betacom.sb.dto.output.BicycleDTO;
import com.betacom.sb.dto.output.CarDTO;
import com.betacom.sb.dto.output.MotorcycleDTO;
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
//				.car(vehicle.getCar() == null ? null : CarDTO.builder()
//						.id(vehicle.getCar().getId())
//						.licensePlate(vehicle.getCar().getLicensePlate())
//						.displacementCc(vehicle.getCar().getDisplacementCc())
//						.doorCount(vehicle.getCar().getDoorCount())
//						.build())
//				.motorcycle(vehicle.getMotorcycle() == null ? null : MotorcycleDTO.builder()
//						.id(vehicle.getMotorcycle().getId())
//						.licensePlate(vehicle.getMotorcycle().getLicensePlate())
//						.displacementCc(vehicle.getMotorcycle().getDisplacementCc())
//						.build())
//				.bicycle(vehicle.getBicycle() == null ? null : BicycleDTO.builder()
//						.id(vehicle.getBicycle().getId())
//						.gearCount(vehicle.getBicycle().getGearCount())
//						.brakeType(vehicle.getBicycle().getBrakeType())
//						.suspensionType(vehicle.getBicycle().getSuspensionType())
//						.isFoldable(vehicle.getBicycle().getIsFoldable())
//						.build())
				.build();
	}
}

