package com.betacom.sb.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.sb.dto.input.CarReq;
import com.betacom.sb.dto.output.ResponseDTO;
import com.betacom.sb.services.interfaces.ICarServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/rest/car")
public class CarController {
	
	private final ICarServices servCar;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> create(@RequestBody(required = true) CarReq req) throws Exception{
		ResponseDTO r = new ResponseDTO();
		HttpStatus status = HttpStatus.OK;
		try {
			servCar.create(req);
			r.setMsg("created");
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
	
	@PatchMapping("/update")
	public ResponseEntity<ResponseDTO> update(@RequestBody(required = true) CarReq req) throws Exception{
		ResponseDTO r = new ResponseDTO();
		HttpStatus status = HttpStatus.OK;
		try {
			servCar.update(req);
			r.setMsg("updated");
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDTO> delete(@RequestParam(required = true) Long id) throws Exception{
		ResponseDTO r = new ResponseDTO();
		HttpStatus status = HttpStatus.OK;
		try {
			servCar.delete(id);
			r.setMsg("deleted");
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
	
	@GetMapping("/list")
	public ResponseEntity<Object> list() throws Exception{
		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
			r = servCar.list();
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
			r = servCar.getById(id);
		} catch (Exception e) {
			r = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
	
	
}
