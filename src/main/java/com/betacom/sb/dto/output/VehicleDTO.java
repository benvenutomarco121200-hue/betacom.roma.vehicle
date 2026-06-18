package com.betacom.sb.dto.output;

import com.fasterxml.jackson.annotation.JsonInclude;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleDTO {
	private Long id;
	private Integer wheelCount;
	private String color;
	private String brand;
	private Integer productionYear;
	private String model;
	private String vehicleType;
	private String category;
	private String fuelType; 
    private CarDTO car;
    private MotorcycleDTO motorcycle;
    private BicycleDTO bicycle;
}
