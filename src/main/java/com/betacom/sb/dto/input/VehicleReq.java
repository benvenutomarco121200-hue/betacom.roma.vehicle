package com.betacom.sb.dto.input;

import com.betacom.sb.enums.Category;
import com.betacom.sb.enums.FuelType;
import com.betacom.sb.enums.VehicleType;

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
public class VehicleReq {
	
	private Long id;
	
	@NotNull(groups = ValidationGroups.Create.class)
    private VehicleType vehicleType;
	
	@NotNull(groups = ValidationGroups.Create.class)
    private Category category;
	
	@NotNull(groups = ValidationGroups.Create.class)
    private Integer wheelCount;
	
	@NotNull(groups = ValidationGroups.Create.class)
    private String color;
	
	@NotNull(groups = ValidationGroups.Create.class)
    private String brand;
	
	@NotNull(groups = ValidationGroups.Create.class)
    private Integer productionYear;
	
	@NotNull(groups = ValidationGroups.Create.class)
    private String model;
	
	@NotNull(groups = ValidationGroups.Create.class)
    private FuelType fuelType;
}
