package com.betacom.sb.mapping;

import java.util.List;

import com.betacom.sb.dto.output.CarDTO;
import com.betacom.sb.dto.output.VehicleDTO;
import com.betacom.sb.models.Car;

public class CarMap {
	public static List<CarDTO> buildListCarDTO(List<Car> lC){
		return lC.stream().map(v -> buildCarDTO(v)).toList();
	}
	
	
	public static CarDTO buildCarDTO(Car car) {
		return CarDTO.builder()
				.id(car.getId())
				.licensePlate(car.getLicensePlate())
				.displacementCc(car.getDisplacementCc())
				.doorCount(car.getDoorCount())
				.vehicle(VehicleDTO.builder()
						.id(car.getVehicle().getId())
						.vehicleType(car.getVehicle().getVehicleType())
						.category(car.getVehicle().getCategory())
						.wheelCount(car.getVehicle().getWheelCount())
						.color(car.getVehicle().getColor())
						.brand(car.getVehicle().getBrand())
						.productionYear(car.getVehicle().getProductionYear())
						.model(car.getVehicle().getModel())
						.build())
				.build();
	}
}
