package com.betacom.sb.dto.input;

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
	private Integer wheelCount;
	private String color;
	private String brand;
	private Integer productionYear;
	private String model;
	private String vehicleType;
	private String category;
	private String fuelType;
}
