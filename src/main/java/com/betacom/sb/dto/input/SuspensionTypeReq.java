package com.betacom.sb.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class SuspensionTypeReq {

	private Long id;
	
	@NotNull (groups = ValidationGroups.Create.class , message ="suspension_invalid")
	@NotBlank (groups = ValidationGroups.Create.class , message ="suspension_invalid")
	private String suspensionType;
}
