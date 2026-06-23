package com.betacom.sb.suspensionType;

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

import com.betacom.sb.dto.input.SuspensionTypeReq;
import com.betacom.sb.dto.output.ResponseDTO;
import com.betacom.sb.dto.output.SuspensionTypeDTO;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SuspensionTypeTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	@Order(1)
	public void createSuspensionTypeTest() throws Exception{
		log.debug("createSuspensionTypeTest");
		SuspensionTypeReq req = new SuspensionTypeReq();
		req.setSuspensionType("qualcosa");
		
		mockMvc.perform(post("/rest/suspensionType/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
	}
	
	@Test
	@Order(2)
	public void createSuspensionTypeTestError() throws Exception{
		log.debug("createSuspensionTypeTestError");
		SuspensionTypeReq req = new SuspensionTypeReq();
		
		MvcResult result =  mockMvc.perform(post("/rest/suspensionType/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				)
		.andExpect(status().isBadRequest())		 
		.andReturn();
		
		String json = result.getResponse().getContentAsString();
		ResponseDTO dto = objectMapper.readValue(json, ResponseDTO.class);
		
		log.debug("suspensionType create: {}", dto.getMsg());
	}
	
	@Test
	@Order(3)
	public void listAllSuspensionTypeTest() throws Exception{
		log.debug("listAllSuspensionTypeTest");
		
		MvcResult result = mockMvc.perform(get("/rest/suspensionType/list"))
	            .andExpect(status().isOk())
	            .andReturn();
		  
		String json = result.getResponse().getContentAsString();
		
		List<SuspensionTypeDTO> lS = objectMapper.readValue(
	            json,
	            new TypeReference<List<SuspensionTypeDTO>>() {}
	    );
		
		assertFalse(lS.isEmpty());
		
		lS.forEach(s -> log.debug(s.toString()));
	}
}
