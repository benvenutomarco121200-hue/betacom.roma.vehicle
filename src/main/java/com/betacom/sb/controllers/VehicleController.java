package com.betacom.sb.controllers;

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
	
	@GetMapping("/listAll")
	public ResponseEntity<Object> listAll() throws Exception{
		return ResponseEntity.ok(servVehicle.listAll());
	}
	
	@GetMapping("/list")
	public ResponseEntity<Object> list(
			@RequestParam (required = false)  Long id,
			@RequestParam (required = false)  String vehicleType,
			@RequestParam (required = false)  String category,
			@RequestParam (required = false)  String fuelType,
			@RequestParam (required = false)  String color,
			@RequestParam (required = false)  String brand,
			@RequestParam (required = false)  String model,
			@RequestParam (required = false)  String licensePlate,
			@RequestParam (required = false)  Integer doorCount
			) throws Exception{
		return ResponseEntity.ok(servVehicle.find(id, vehicleType, category, fuelType, color, brand, model, licensePlate, doorCount));
	}
	
	@GetMapping("/getById")
	public ResponseEntity<Object> getById(@RequestParam (required = false)  Long id) throws Exception{
		return ResponseEntity.ok(servVehicle.getById(id));
		
	}
}
