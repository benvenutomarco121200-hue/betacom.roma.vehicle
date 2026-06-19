package com.betacom.sb.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.FuelTypeReq;
import com.betacom.sb.dto.output.FuelTypeDTO;

import exceptions.BetacomRomaException;

@Service
public interface IFuelTypeServices {

	void create(FuelTypeReq req) throws BetacomRomaException;
	void delete(Long id) throws BetacomRomaException;
	
	List<FuelTypeDTO> list();
}
