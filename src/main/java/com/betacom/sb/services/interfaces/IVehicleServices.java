package com.betacom.sb.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.output.VehicleDTO;

@Service
public interface IVehicleServices {
	VehicleDTO getById(Long id) throws Exception;
	List<VehicleDTO> list() throws Exception;
	
	List<VehicleDTO> selectByColor(String color) throws Exception;
}
