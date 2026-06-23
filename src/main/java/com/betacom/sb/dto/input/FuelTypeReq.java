package com.betacom.sb.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class FuelTypeReq {
	
	private Long id;
	
	@NotNull (groups = ValidationGroups.Create.class , message ="fuel_invalid")
	@NotBlank (groups = ValidationGroups.Create.class , message ="fuel_invalid")
	private String fuelType;
}

