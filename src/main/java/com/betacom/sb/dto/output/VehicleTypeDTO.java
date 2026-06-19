package com.betacom.sb.dto.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class VehicleTypeDTO {

	private Long id;
	private String vehicleType;
}
