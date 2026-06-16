package com.betacom.sb.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.betacom.sb.dto.input.MotorcycleReq;
import com.betacom.sb.dto.output.ResponseDTO;
import com.betacom.sb.services.interfaces.IMotorcycleServices;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MotorcycleController {

	private final IMotorcycleServices servMoto;

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> create(@RequestBody(required = true) MotorcycleReq req) throws Exception{
		ResponseDTO r = new ResponseDTO();
		HttpStatus status = HttpStatus.OK;
		try {
			servMoto.create(req);
			r.setMsg("created");
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> update(@RequestBody(required = true) MotorcycleReq req) throws Exception{
		ResponseDTO r = new ResponseDTO();
		HttpStatus status = HttpStatus.OK;
		try {
			servMoto.update(req);
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
			servMoto.delete(id);
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
			r = servMoto.list();
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
			r = servMoto.getById(id);
		} catch (Exception e) {
			r = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
	
	
}
