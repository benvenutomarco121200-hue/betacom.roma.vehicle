package com.betacom.sb.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.betacom.sb.dto.output.ResponseDTO;
import com.betacom.sb.services.interfaces.IMessageServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionManager {
	
	private final IMessageServices servMessage;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDTO> handleException(Exception e){
		return ResponseEntity.badRequest()
				.body(ResponseDTO.builder()
						.msg(servMessage.get(e.getMessage()))
						.build()
						);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO> handleValidationException(MethodArgumentNotValidException e) {
		  String msg = e.getBindingResult()
	                .getFieldErrors()
	                .stream()
	                .findFirst()
	                .map(FieldError::getDefaultMessage)
	                .orElse("Errore di validazione");

		  return ResponseEntity.badRequest()
					.body(ResponseDTO.builder()
							.msg(servMessage.get(msg))
							.build()
							);  
	}
	
	
}
