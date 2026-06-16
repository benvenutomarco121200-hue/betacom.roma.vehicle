package com.betacom.sb.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.CarReq;
import com.betacom.sb.dto.input.MotorcycleReq;
import com.betacom.sb.dto.output.CarDTO;
import com.betacom.sb.dto.output.MotorcycleDTO;

@Service
public interface IMotorcycleServices {

	void create(MotorcycleReq req) throws Exception;
	void update(MotorcycleReq req) throws Exception;
	void delete(Long id) throws Exception;
	
	MotorcycleDTO getById(Long id) throws Exception;
	List<MotorcycleDTO> list() throws Exception;
}
