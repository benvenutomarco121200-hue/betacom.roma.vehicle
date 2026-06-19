package com.betacom.sb.dto.input;

import com.betacom.sb.dto.output.CategoryDTO;
import com.betacom.sb.dto.output.FuelTypeDTO;
import com.betacom.sb.dto.output.VehicleTypeDTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class VehicleReq {
	
	private Long id;
	
	@NotNull (groups = ValidationGroups.Create.class , message ="ruote_invalid")
	@Min(value = 2, groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, message = "ruote_invalid")
	@Max(value = 6, groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, message = "ruote_invalid")
	private Integer wheelCount;
	
	@NotNull (groups = ValidationGroups.Create.class , message ="colore_invalid")
	private String color;
	
	@NotNull (groups = ValidationGroups.Create.class , message ="marca_invalid")
	private String brand;
	
	@NotNull (groups = ValidationGroups.Create.class , message ="anno_invalid")
	private Integer productionYear;
	
	@NotNull (groups = ValidationGroups.Create.class , message ="model_invalid")
	@NotBlank (groups = ValidationGroups.Create.class , message ="model_invalid")
	private String model;
	

	@NotNull (groups = ValidationGroups.Create.class , message ="tipo_veicolo_invalid")
	private VehicleTypeDTO vehicleType;
	
	@NotNull (groups = ValidationGroups.Create.class , message ="cat_invalid")
	private CategoryDTO category;
	
	@NotNull (groups = ValidationGroups.Create.class , message ="alim_invalid")
	private FuelTypeDTO fuelType;

}
