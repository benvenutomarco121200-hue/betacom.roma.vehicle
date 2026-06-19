package com.betacom.sb.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.VehicleTypeReq;
import com.betacom.sb.dto.output.VehicleTypeDTO;

import exceptions.BetacomRomaException;

@Service
public interface IVehicleTypeServices {

	void create(VehicleTypeReq req) throws BetacomRomaException;
	void delete(Long id) throws BetacomRomaException;
	
	List<VehicleTypeDTO> list();
}
