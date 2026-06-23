package com.betacom.sb.bicycle;

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

import com.betacom.sb.dto.input.BicycleReq;
import com.betacom.sb.dto.output.BicycleDTO;
import com.betacom.sb.dto.output.BrakeTypeDTO;
import com.betacom.sb.dto.output.CategoryDTO;
import com.betacom.sb.dto.output.FuelTypeDTO;
import com.betacom.sb.dto.output.ResponseDTO;
import com.betacom.sb.dto.output.SuspensionTypeDTO;
import com.betacom.sb.dto.output.VehicleTypeDTO;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BicycleTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	@Test
	@Order(1)
	public void createBicycleTest() throws Exception{
		log.debug("createBicylceTest");
		
		BicycleReq req = new BicycleReq();
		
		req.setGearCount(3);
		req.setIsFoldable(true);
		req.setWheelCount(2);
		req.setColor("Red");
		req.setBrand("brand1");
		req.setProductionYear(2000);
		req.setModel("model3");

        FuelTypeDTO fuelType = new FuelTypeDTO();
        fuelType.setId(1L);
        req.setFuelType(fuelType);

        CategoryDTO category = new CategoryDTO();
        category.setId(1L);
        req.setCategory(category);

        VehicleTypeDTO vehicleType = new VehicleTypeDTO();
        vehicleType.setId(1L);
        req.setVehicleType(vehicleType);
        
        BrakeTypeDTO brakeType = new BrakeTypeDTO();
        brakeType.setId(1L);
        req.setBrakeType(brakeType);
        
        SuspensionTypeDTO suspensionType = new SuspensionTypeDTO();
        suspensionType.setId(1L);
        req.setSuspensionType(suspensionType);
        
		mockMvc.perform(post("/rest/bicycle/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
	}
	
	@Test
	@Order(2)
	public void createBicycleTestError() throws Exception{
		log.debug("createBicylceTestError");
		BicycleReq req = new BicycleReq();
		
		MvcResult result =  mockMvc.perform(post("/rest/bicycle/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				)
		.andExpect(status().isBadRequest())		 
		.andReturn();
		
		String json = result.getResponse().getContentAsString();
		ResponseDTO dto = objectMapper.readValue(json, ResponseDTO.class);
		
		log.debug("bicycle create: {}", dto.getMsg());
	}
	
	@Test
	@Order(3)
	public void listAllBicycleTest() throws Exception{
		log.debug("listAllBicylceTest");
		
		MvcResult result = mockMvc.perform(get("/rest/bicycle/list"))
	            .andExpect(status().isOk())
	            .andReturn();
		  
		String json = result.getResponse().getContentAsString();
		
		List<BicycleDTO> lS = objectMapper.readValue(
	            json,
	            new TypeReference<List<BicycleDTO>>() {}
	    );
		
		assertFalse(lS.isEmpty());
		
		lS.forEach(s -> log.debug(s.toString()));
	}
	
	@Test
	@Order(4)
	public void updateBicycleTest() throws Exception{
		log.debug("updateBicycleTest");

		BicycleReq req = new BicycleReq();
		req.setId(1L);
		req.setColor("Yellow");

		mockMvc.perform(patch("/rest/bicycle/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
	}
}
