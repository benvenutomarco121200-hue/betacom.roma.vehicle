package com.betacom.sb.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.CategoryReq;
import com.betacom.sb.dto.output.CategoryDTO;

import exceptions.BetacomRomaException;

@Service
public interface ICategoryServices {

	void create(CategoryReq req) throws BetacomRomaException;
	void delete(Long id) throws BetacomRomaException;
	
	List<CategoryDTO> list();
}
