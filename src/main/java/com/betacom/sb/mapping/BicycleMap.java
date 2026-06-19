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
                .brakeType(BrakeTypeMap.buildBrakeTypeDTO(bicycle.getBrakeType()))
                .suspensionType(SuspensionTypeMap.buildSuspensionTypeDTO(bicycle.getSuspensionType()))
                .isFoldable(bicycle.getIsFoldable())
                
                .vehicle(VehicleDTO.builder()               		
						.id(bicycle.getVehicle().getId())
						.vehicleType(VehicleTypeMap.buildVehicleTypeDTO(bicycle.getVehicle().getVehicleType()))
						.category(CategoryMap.buildCategoryDTO(bicycle.getVehicle().getCategory()))
						.wheelCount(bicycle.getVehicle().getWheelCount())
						.color(bicycle.getVehicle().getColor())
						.brand(bicycle.getVehicle().getBrand())
						.productionYear(bicycle.getVehicle().getProductionYear())
						.model(bicycle.getVehicle().getModel())
						.build())
                .build();
    }
}