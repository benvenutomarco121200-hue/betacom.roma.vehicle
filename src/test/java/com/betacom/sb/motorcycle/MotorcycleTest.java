package com.betacom.sb.motorcycle;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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

import com.betacom.sb.dto.input.MotorcycleReq;
import com.betacom.sb.dto.output.CategoryDTO;
import com.betacom.sb.dto.output.FuelTypeDTO;
import com.betacom.sb.dto.output.MotorcycleDTO;
import com.betacom.sb.dto.output.ResponseDTO;
import com.betacom.sb.dto.output.VehicleTypeDTO;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MotorcycleTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	@Order(1)
	public void createMotorcycleTest() throws Exception{
		log.debug("createMotorcycleTest");
		
		MotorcycleReq req = new MotorcycleReq();
		
		req.setBrand("BMW");
        req.setModel("M3");
        req.setColor("Blu");
        req.setWheelCount(4);
        req.setProductionYear(2023);

        req.setLicensePlate("ZZ99900");
        req.setDisplacementCc(3000);

        FuelTypeDTO fuelType = new FuelTypeDTO();
        fuelType.setId(1L);
        req.setFuelType(fuelType);

        CategoryDTO category = new CategoryDTO();
        category.setId(1L);
        req.setCategory(category);

        VehicleTypeDTO vehicleType = new VehicleTypeDTO();
        vehicleType.setId(1L);
        req.setVehicleType(vehicleType);
        
        mockMvc.perform(post("/rest/motorcycle/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
	}
	
	@Test
	@Order(2)
	public void createMotorcycleTestError() throws Exception{
		log.debug("createMotorcycleTestError");
		MotorcycleReq req = new MotorcycleReq();
		
		MvcResult result =  mockMvc.perform(post("/rest/motorcycle/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				)
		.andExpect(status().isBadRequest())		 
		.andReturn();
		
		String json = result.getResponse().getContentAsString();
		ResponseDTO dto = objectMapper.readValue(json, ResponseDTO.class);
		
		log.debug("motorcycle create: {}", dto.getMsg());
	}
	
	@Test
	@Order(3)
	public void listAllMotorcycleTest() throws Exception{
		log.debug("listAllBicylceTest");
		
		MvcResult result = mockMvc.perform(get("/rest/motorcycle/list"))
	            .andExpect(status().isOk())
	            .andReturn();
		  
		String json = result.getResponse().getContentAsString();
		
		List<MotorcycleDTO> lS = objectMapper.readValue(
	            json,
	            new TypeReference<List<MotorcycleDTO>>() {}
	    );
		
		assertFalse(lS.isEmpty());
		
		lS.forEach(s -> log.debug(s.toString()));
	}
	
	@Test
	@Order(4)
	public void updateMotorcycleTest() throws Exception{
		log.debug("updateMotorcycleTest");

		MotorcycleReq req = new MotorcycleReq();
		req.setId(1L);
		req.setColor("Yellow");

		mockMvc.perform(patch("/rest/motorcycle/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
	}
	
}
