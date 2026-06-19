package com.betacom.sb.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.sb.dto.input.FuelTypeReq;
import com.betacom.sb.dto.input.ValidationGroups;
import com.betacom.sb.dto.output.ResponseDTO;
import com.betacom.sb.services.interfaces.IFuelTypeServices;
import com.betacom.sb.services.interfaces.IMessageServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/rest/fuelType")
public class FuelTypeController {

	private final IFuelTypeServices servFuelType;
	private final IMessageServices servMessage;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> create(@RequestBody(required = true) @Validated(ValidationGroups.Create.class) FuelTypeReq req) 
			throws Exception{
		ResponseDTO r = new ResponseDTO();
		servFuelType.create(req);
		r.setMsg(servMessage.get("rest_created"));
		return ResponseEntity.ok(r);		
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable(required = true)  Long id) throws Exception{
		ResponseDTO r = new ResponseDTO();
		servFuelType.delete(id);
		r.setMsg(servMessage.get("rest_deleted"));
		return ResponseEntity.ok(r);		
	}
	
	@GetMapping("/list")
	public ResponseEntity<Object> list() throws Exception{
		return ResponseEntity.ok(servFuelType.list());
	}
}