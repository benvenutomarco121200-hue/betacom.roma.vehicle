package com.betacom.sb.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.sb.services.interfaces.IVehicleServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/rest/vehicle")
public class VehicleController {
	private final IVehicleServices servVehicle;
	
	@GetMapping("/list")
	public ResponseEntity<Object> list() throws Exception{
		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
			r = servVehicle.list();
		} catch (Exception e) {
			r = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
	
	@GetMapping("/getById")
	public ResponseEntity<Object> getById(@RequestParam(required = true) Long id) throws Exception{
		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
			r = servVehicle.getById(id);
		} catch (Exception e) {
			r = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
	
	
	@GetMapping("/selectByColor")
	public ResponseEntity<Object> selectByColor(@RequestParam(required = true) String color) throws Exception{
		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
			r = servVehicle.selectByColor(color);
		} catch (Exception e) {
			r = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
}
