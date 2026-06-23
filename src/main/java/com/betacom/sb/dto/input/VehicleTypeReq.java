package com.betacom.sb.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTypeReq {

	private Long id;
	
	@NotNull (groups = ValidationGroups.Create.class , message ="vehicle_invalid")
	@NotBlank (groups = ValidationGroups.Create.class , message ="vehicle_invalid")
	private String vehicleType;
}
