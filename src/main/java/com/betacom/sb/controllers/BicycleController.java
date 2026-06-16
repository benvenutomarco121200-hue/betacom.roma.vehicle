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

import com.betacom.sb.dto.input.BicycleReq;
import com.betacom.sb.dto.output.ResponseDTO;
import com.betacom.sb.services.interfaces.IBicycleServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/rest/bicycle")
public class BicycleController {

    private final IBicycleServices servBicycle;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody(required = true) BicycleReq req) {
        ResponseDTO r = new ResponseDTO();
        HttpStatus status = HttpStatus.OK;
        try {
            servBicycle.create(req);
            r.setMsg("created");
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseDTO> update(@RequestBody(required = true) BicycleReq req) {
        ResponseDTO r = new ResponseDTO();
        HttpStatus status = HttpStatus.OK;
        try {
            servBicycle.update(req);
            r.setMsg("updated");
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> delete(@RequestParam(required = true) Long id) {
        ResponseDTO r = new ResponseDTO();
        HttpStatus status = HttpStatus.OK;
        try {
            servBicycle.delete(id);
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
			r = servBicycle.list();
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
			r = servBicycle.getById(id);
		} catch (Exception e) {
			r = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
}