package com.betacom.sb.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.sb.dto.input.CarReq;
import com.betacom.sb.dto.input.VehicleReq;
import com.betacom.sb.dto.output.CarDTO;
import com.betacom.sb.dto.output.ResponseDTO;
import com.betacom.sb.services.interfaces.ICarServices;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/rest/car")
public class CarController {
	
	private final ICarServices servCar;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> create(@RequestBody(required = true) CarReq carReq,@RequestBody(required = true) VehicleReq vehicleReq) throws Exception{
		ResponseDTO r = new ResponseDTO();
		HttpStatus status = HttpStatus.OK;
		try {
			servCar.create(carReq, vehicleReq);
			r.setMsg("created");
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
	
}
