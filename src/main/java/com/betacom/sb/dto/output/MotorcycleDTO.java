package com.betacom.sb.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MotorcycleDTO {
	private Long id;
	private String licensePlate;
	private Integer displacementCc;
	
	private VehicleDTO vehicle;
}
