package com.betacom.sb.dto.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FuelTypeDTO {

	private Long id;
	private String fuelType;
}
