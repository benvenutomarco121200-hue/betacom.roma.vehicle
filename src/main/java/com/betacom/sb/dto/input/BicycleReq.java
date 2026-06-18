package com.betacom.sb.dto.input;

import com.betacom.sb.models.BrakeType;
import com.betacom.sb.models.Category;
import com.betacom.sb.models.FuelType;
import com.betacom.sb.models.SuspensionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BicycleReq {
    private Long id;
    private Integer gearCount;
    private String brakeType;
    private String suspensionType;
    private Boolean isFoldable;


    private String category;
    private Integer wheelCount;
    private String color;
    private String brand;
    private Integer productionYear;
    private String model;
    private String fuelType;
}