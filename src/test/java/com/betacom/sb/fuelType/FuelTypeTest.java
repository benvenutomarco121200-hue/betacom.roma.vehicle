package com.betacom.sb.fuelType;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.betacom.sb.dto.input.FuelTypeReq;
import com.betacom.sb.dto.output.FuelTypeDTO;
import com.betacom.sb.dto.output.ResponseDTO;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FuelTypeTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	@Order(1)
	public void createFuelTypeTest() throws Exception{
		log.debug("createFuelTypeTest");
		FuelTypeReq req = new FuelTypeReq();
		req.setFuelType("lpg");
		req.setId((long) 3);
		
		mockMvc.perform(post("/rest/fuelType/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
	}
	
	@Test
	@Order(2)
	public void createFuelTypeTestError() throws Exception{
		log.debug("createFuelTypeTestError");
		FuelTypeReq req = new FuelTypeReq();
		
		MvcResult result =  mockMvc.perform(post("/rest/fuelType/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				)
		.andExpect(status().isBadRequest())		 
		.andReturn();
		
		String json = result.getResponse().getContentAsString();
		ResponseDTO dto = objectMapper.readValue(json, ResponseDTO.class);
		
		log.debug("rc create :{}", dto.getMsg());
	}
	
	
	@Test
	@Order(3)
	public void listAllFuelType() throws Exception{
		log.debug("listAllFuelType");
		
		MvcResult result = mockMvc.perform(get("/rest/fuelType/list"))
	            .andExpect(status().isOk())
	            .andReturn();
		  
		String json = result.getResponse().getContentAsString();
		
		List<FuelTypeDTO> lS = objectMapper.readValue(
	            json,
	            new TypeReference<List<FuelTypeDTO>>() {}
	    );
		
		assertFalse(lS.isEmpty());
		
		lS.forEach(s -> log.debug(s.toString()));
	}
	
}
