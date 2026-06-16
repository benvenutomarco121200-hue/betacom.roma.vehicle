package com.betacom.sb.dto.input;

import com.betacom.sb.dto.output.BicycleDTO;
import com.betacom.sb.dto.output.CarDTO;
import com.betacom.sb.dto.output.MotorcycleDTO;
import com.betacom.sb.dto.output.VehicleDTO;
import com.betacom.sb.enums.Category;
import com.betacom.sb.enums.FuelType;
import com.betacom.sb.enums.VehicleType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
