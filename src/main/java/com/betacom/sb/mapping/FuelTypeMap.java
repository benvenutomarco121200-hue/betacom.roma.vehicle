package com.betacom.sb.mapping;

import java.util.List;

import com.betacom.sb.dto.output.FuelTypeDTO;
import com.betacom.sb.models.FuelType;

public class FuelTypeMap {

	public static List<FuelTypeDTO> buildFuelTypeDTOList(List<FuelType> lF){
		return lF.stream()
				.map(t -> buildFuelTypeDTO(t)
						).toList();		
	}
	
	public static FuelTypeDTO buildFuelTypeDTO(FuelType f) {
		return FuelTypeDTO.builder()
				.id(f.getId())
				.fuelType(f.getFuel())
				.build();
	}
}
