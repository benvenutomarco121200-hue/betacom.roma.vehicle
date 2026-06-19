package com.betacom.sb.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.BrakeTypeReq;
import com.betacom.sb.dto.output.BrakeTypeDTO;

import exceptions.BetacomRomaException;

@Service
public interface IBrakeTypeServices {

	void create(BrakeTypeReq req) throws BetacomRomaException;
	void delete(Long id) throws BetacomRomaException;
	
	List<BrakeTypeDTO> list();
}
