package com.betacom.sb.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.SuspensionTypeReq;
import com.betacom.sb.dto.output.SuspensionTypeDTO;

import exceptions.BetacomRomaException;

@Service
public interface ISuspensionTypeServices {

	void create(SuspensionTypeReq req) throws BetacomRomaException;
	void delete(Long id) throws BetacomRomaException;
	
	List<SuspensionTypeDTO> list();
}
