package com.betacom.sb.dto.input;

import com.betacom.sb.enums.BrakeType;
import com.betacom.sb.enums.Category;
import com.betacom.sb.enums.FuelType;
import com.betacom.sb.enums.SuspensionType;
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
    private BrakeType brakeType;
    private SuspensionType suspensionType;
    private Boolean isFoldable;


    private Category category;
    private Integer wheelCount;
    private String color;
    private String brand;
    private Integer productionYear;
    private String model;
    private FuelType fuelType;
}