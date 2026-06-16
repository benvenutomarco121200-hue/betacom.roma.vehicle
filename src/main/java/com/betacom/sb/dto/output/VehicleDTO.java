package com.betacom.sb.dto.output;

import com.betacom.sb.enums.Category;
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
public class VehicleDTO {
	private Long id;
    private VehicleType vehicleType;
    private Category category;
    private Integer wheelCount;
    private String color;
    private String brand;
    private Integer productionYear;
    private String model;
//    private CarDTO car;
//    private MotorcycleDTO motorcycle;
//    private BicycleDTO bicycle;
}
