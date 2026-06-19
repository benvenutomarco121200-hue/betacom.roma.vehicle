package com.betacom.sb.mapping;

import java.util.List;

import com.betacom.sb.dto.output.BrakeTypeDTO;
import com.betacom.sb.models.BrakeType;

public class BrakeTypeMap {

	public static List<BrakeTypeDTO> buildBrakeTypeDTOList(List<BrakeType> lB){
		return lB.stream()
				.map(t -> buildBrakeTypeDTO(t)
						).toList();		
	}
	
	public static BrakeTypeDTO buildBrakeTypeDTO(BrakeType b) {
		return BrakeTypeDTO.builder()
				.id(b.getId())
				.brakeType(b.getBrakeType())
				.build();
	}
}
