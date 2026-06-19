package com.betacom.sb.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class CategoryReq {

	private Long id;
	
	@NotNull (groups = ValidationGroups.Create.class , message ="category_invalid")
	@NotBlank (groups = ValidationGroups.Create.class , message ="category_invalid")
	private String category;
}
