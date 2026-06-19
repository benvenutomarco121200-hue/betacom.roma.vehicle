package com.betacom.sb.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.sb.dto.input.MotorcycleReq;
import com.betacom.sb.dto.input.ValidationGroups;
import com.betacom.sb.dto.output.ResponseDTO;
import com.betacom.sb.services.interfaces.IMessageServices;
import com.betacom.sb.services.interfaces.IMotorcycleServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/rest/motorcycle")
public class MotorcycleController {

	private final IMotorcycleServices servMotorcycle;
	private final IMessageServices servMessage; 
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> create(@RequestBody(required = true) @Validated(ValidationGroups.Create.class) MotorcycleReq req) 
			throws Exception{
		ResponseDTO r = new ResponseDTO();
		servMotorcycle.create(req);
		r.setMsg(servMessage.get("rest_created"));
		return ResponseEntity.ok(r);		
	}
	
	@PatchMapping("/update")
	public ResponseEntity<ResponseDTO> update(@RequestBody(required = true) @Validated(ValidationGroups.Update.class) MotorcycleReq req) 
			throws Exception{
		ResponseDTO r = new ResponseDTO();
		servMotorcycle.update(req);
		r.setMsg(servMessage.get("rest_updated"));
		return ResponseEntity.ok(r);		
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable(required = true)  Long id) throws Exception{
		ResponseDTO r = new ResponseDTO();
		servMotorcycle.delete(id);
		r.setMsg(servMessage.get("rest_deleted"));
		return ResponseEntity.ok(r);		
	}
	
	@GetMapping("/list")
	public ResponseEntity<Object> listAll() throws Exception{
		return ResponseEntity.ok(servMotorcycle.list());
	}
	
	@GetMapping("/getById")
	public ResponseEntity<Object> getById(@RequestParam (required = false)  Long id) throws Exception{
		return ResponseEntity.ok(servMotorcycle.getById(id));
		
	}
	
	
	
	
}
