package com.betacom.sb.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MotorcycleReq extends VehicleReq{

	@NotBlank (groups = ValidationGroups.Create.class , message ="targa_invalid")
	@Pattern(
		    regexp = "^[A-Z]{2}[0-9]{5}$",
		    groups = {ValidationGroups.Create.class, ValidationGroups.Update.class},
		    message = "targa_invalid"
		)
	private String licensePlate;
	
	@NotNull (groups = ValidationGroups.Create.class , message ="cc_invalid")
	private Integer displacementCc;
	

}

