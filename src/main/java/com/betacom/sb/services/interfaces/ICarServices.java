package com.betacom.sb.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.CarReq;
import com.betacom.sb.dto.input.VehicleReq;
import com.betacom.sb.dto.output.CarDTO;

@Service
public interface ICarServices {

	CarDTO getById(Long id) throws Exception;
	List<CarDTO> list() throws Exception;
	
	void create(CarReq carReq, VehicleReq vehicleReq) throws Exception;
	void update(CarReq req) throws Exception;
	void delete(Long id) throws Exception;
}
