package com.betacom.sb.mapping;

import java.util.List;

import com.betacom.sb.dto.output.MotorcycleDTO;
import com.betacom.sb.dto.output.VehicleDTO;
import com.betacom.sb.models.Motorcycle;

public class MotorcycleMap {

	public static List<MotorcycleDTO> buildListMotorcycleDTO(List<Motorcycle> lM){
		return lM.stream().map(v -> buildMotorcycleDTO(v)).toList();
	}
	
	
	public static MotorcycleDTO buildMotorcycleDTO(Motorcycle moto) {
		return MotorcycleDTO.builder()
				.id(moto.getId())
				.licensePlate(moto.getLicensePlate())
				.displacementCc(moto.getDisplacementCc())
				.vehicle(VehicleDTO.builder()
					.id(moto.getVehicle().getId())
					.vehicleType(moto.getVehicle().getVehicleType())
					.category(moto.getVehicle().getCategory())
					.wheelCount(moto.getVehicle().getWheelCount())
					.color(moto.getVehicle().getColor())
					.brand(moto.getVehicle().getBrand())
					.productionYear(moto.getVehicle().getProductionYear())
					.model(moto.getVehicle().getModel())
					.build())
				.build();
	}
}
