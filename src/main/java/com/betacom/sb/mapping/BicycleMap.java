package com.betacom.sb.mapping;

import java.util.List;

import com.betacom.sb.dto.output.BicycleDTO;
import com.betacom.sb.models.Bicycle;

public class BicycleMap {

    public static List<BicycleDTO> buildListBicycleDTO(List<Bicycle> list) {
        return list.stream().map(b -> buildBicycleDTO(b)).toList();
    }

    public static BicycleDTO buildBicycleDTO(Bicycle bicycle) {
        return BicycleDTO.builder()
                .id(bicycle.getId())
                .gearCount(bicycle.getGearCount())
                .brakeType(bicycle.getBrakeType())
                .suspensionType(bicycle.getSuspensionType())
                .isFoldable(bicycle.getIsFoldable())
                .build();
    }
}