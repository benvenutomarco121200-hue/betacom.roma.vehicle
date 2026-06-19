package com.betacom.sb.mapping;

import java.util.List;

import com.betacom.sb.dto.output.SuspensionTypeDTO;
import com.betacom.sb.models.SuspensionType;

public class SuspensionTypeMap {

	public static List<SuspensionTypeDTO> buildSuspensionTypeDTOList(List<SuspensionType> lS){
		return lS.stream()
				.map(t -> buildSuspensionTypeDTO(t)
						).toList();		
	}
	
	public static SuspensionTypeDTO buildSuspensionTypeDTO(SuspensionType s) {
		return SuspensionTypeDTO.builder()
				.id(s.getId())
				.suspensionType(s.getSuspensionType())
				.build();
	}
}
