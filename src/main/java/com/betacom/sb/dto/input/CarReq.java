package com.betacom.sb.dto.input;

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
public class CarReq extends VehicleReq{
	@NotNull(groups = ValidationGroups.Update.class)
	private Long id;
	
	@NotNull(groups = ValidationGroups.Create.class)
	private String licensePlate;
	
	@NotNull(groups = ValidationGroups.Create.class)
	private Integer displacementCc;
	
	@NotNull(groups = ValidationGroups.Create.class)
	private Integer doorCount;
	
	
//    private Category category;
//    private Integer wheelCount;
//    private String color;
//    private String brand;
//    private Integer productionYear;
//    private String model;
//    private FuelType fuelType;
}
