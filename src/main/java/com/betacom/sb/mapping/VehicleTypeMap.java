package com.betacom.sb.mapping;

import java.util.List;

import com.betacom.sb.dto.output.VehicleTypeDTO;
import com.betacom.sb.models.VehicleType;

public class VehicleTypeMap {

	public static List<VehicleTypeDTO> buildVehicleTypeDTOList(List<VehicleType> lV){
		return lV.stream()
				.map(t -> buildVehicleTypeDTO(t)
						).toList();		
	}
	
	public static VehicleTypeDTO buildVehicleTypeDTO(VehicleType v) {
		return VehicleTypeDTO.builder()
				.id(v.getId())
				.vehicleType(v.getVehicleType())
				.build();
	}
}
