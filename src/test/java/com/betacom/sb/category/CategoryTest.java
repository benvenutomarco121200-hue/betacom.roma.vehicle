package com.betacom.sb.category;

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

import com.betacom.sb.dto.input.CategoryReq;
import com.betacom.sb.dto.output.CategoryDTO;
import com.betacom.sb.dto.output.ResponseDTO;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	@Order(1)
	public void createCategoryTest() throws Exception{
		log.debug("createCategoryTest");
		CategoryReq req = new CategoryReq();
		req.setCategory("sedan");
		req.setId((long) 3);
		
		mockMvc.perform(post("/rest/category/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
		
		CategoryReq req2 = new CategoryReq();
		req2.setCategory("sedan2");
		
		mockMvc.perform(post("/rest/category/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req2))
				).andExpect(status().isOk());
	}
	
	@Test
	@Order(2)
	public void createCategoryTestError() throws Exception{
		log.debug("createCategoryTestError");
		CategoryReq req = new CategoryReq();
		
		MvcResult result =  mockMvc.perform(post("/rest/category/create")
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
	public void listAllCategory() throws Exception{
		log.debug("listAllCategory");
		
		MvcResult result = mockMvc.perform(get("/rest/category/list"))
	            .andExpect(status().isOk())
	            .andReturn();
		  
		String json = result.getResponse().getContentAsString();
		
		List<CategoryDTO> lS = objectMapper.readValue(
	            json,
	            new TypeReference<List<CategoryDTO>>() {}
	    );
		
		assertFalse(lS.isEmpty());
		
		lS.forEach(s -> log.debug(s.toString()));
	}
}
