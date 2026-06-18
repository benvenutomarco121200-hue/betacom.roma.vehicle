package com.betacom.sb.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MotorcycleReq extends VehicleReq{
	@NotNull(groups = ValidationGroups.Update.class)
	private Long id;
	
	@NotNull(groups = ValidationGroups.Create.class)
	private String licensePlate;
	
	@NotNull(groups = ValidationGroups.Create.class)
	private Integer displacementCc;
	
//    private Category category;
//    private Integer wheelCount;
//    private String color;
//    private String brand;
//    private Integer productionYear;
//    private String model;
//    private FuelType fuelType;
}

