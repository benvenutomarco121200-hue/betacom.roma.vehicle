package com.betacom.sb.mapping;

import java.util.List;

import com.betacom.sb.dto.output.BicycleDTO;
import com.betacom.sb.dto.output.VehicleDTO;
import com.betacom.sb.models.Bicycle;

public class BicycleMap {

    public static List<BicycleDTO> buildListBicycleDTO(List<Bicycle> list) {
        return list.stream().map(b -> buildBicycleDTO(b)).toList();
    }

    public static BicycleDTO buildBicycleDTO(Bicycle bicycle) {
        return BicycleDTO.builder()
                .id(bicycle.getId())
                .gearCount(bicycle.getGearCount())
                .brakeType(bicycle.getBrakeType() != null ? bicycle.getBrakeType().getBrake() : null)
                .suspensionType(bicycle.getSuspensionType() != null ? bicycle.getSuspensionType().getSuspension() : null)
                .isFoldable(bicycle.getIsFoldable())
                .vehicle(VehicleDTO.builder()               		
						.id(bicycle.getVehicle().getId())
						.vehicleType(bicycle.getVehicle().getVehicleType() != null ? bicycle.getVehicle().getVehicleType().getVehicle() : null)
						.category(bicycle.getVehicle().getCategory() != null ? bicycle.getVehicle().getCategory().getCategory() : null)
						.wheelCount(bicycle.getVehicle().getWheelCount())
						.color(bicycle.getVehicle().getColor())
						.brand(bicycle.getVehicle().getBrand())
						.productionYear(bicycle.getVehicle().getProductionYear())
						.model(bicycle.getVehicle().getModel())
						.build())
                .build();
    }
}