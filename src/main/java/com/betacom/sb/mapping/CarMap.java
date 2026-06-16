package com.betacom.sb.mapping;

import java.util.List;

import com.betacom.sb.dto.output.CarDTO;
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
				//.vehicle(VehicleMap.buildVehicleDTO(car.getVehicle()))
				.build();
	}
}
