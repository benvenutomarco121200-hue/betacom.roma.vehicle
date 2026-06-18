package com.betacom.sb.dto.input;

import com.betacom.sb.models.Category;
import com.betacom.sb.models.FuelType;

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
public class MotorcycleReq {

	private Long id;
	private String licensePlate;
	private Integer displacementCc;
	
    private String category;
    private Integer wheelCount;
    private String color;
    private String brand;
    private Integer productionYear;
    private String model;
    private String fuelType;
}

