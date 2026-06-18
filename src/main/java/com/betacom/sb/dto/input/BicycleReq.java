package com.betacom.sb.dto.input;

import com.betacom.sb.enums.BrakeType;
import com.betacom.sb.enums.SuspensionType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BicycleReq extends VehicleReq{
	@NotNull(groups = ValidationGroups.Update.class)
    private Long id;
	
	@NotNull(groups = ValidationGroups.Create.class)
    private Integer gearCount;
	
	@NotNull(groups = ValidationGroups.Create.class)
    private BrakeType brakeType;
	
	@NotNull(groups = ValidationGroups.Create.class)
    private SuspensionType suspensionType;
	
	@NotNull(groups = ValidationGroups.Create.class)
    private Boolean isFoldable;


//    private Category category;
//    private Integer wheelCount;
//    private String color;
//    private String brand;
//    private Integer productionYear;
//    private String model;
//    private FuelType fuelType;
}