package com.betacom.sb.dto.input;

import com.betacom.sb.enums.Category;
import com.betacom.sb.enums.FuelType;
import com.betacom.sb.enums.VehicleType;

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
    private VehicleType vehicleType;
    private Category category;
    private Integer wheelCount;
    private String color;
    private String brand;
    private Integer productionYear;
    private String model;
    private FuelType fuelType;
}
