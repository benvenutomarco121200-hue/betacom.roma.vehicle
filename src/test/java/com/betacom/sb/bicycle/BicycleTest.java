package com.betacom.sb.bicycle;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import com.betacom.sb.dto.input.FuelTypeReq;
import com.betacom.sb.dto.input.SuspensionTypeReq;
import com.betacom.sb.dto.input.VehicleTypeReq;
import com.betacom.sb.dto.output.BicycleDTO;
import com.betacom.sb.dto.output.BrakeTypeDTO;
import com.betacom.sb.dto.output.CategoryDTO;
import com.betacom.sb.dto.output.FuelTypeDTO;
import com.betacom.sb.dto.output.ResponseDTO;
import com.betacom.sb.dto.output.SuspensionTypeDTO;
import com.betacom.sb.dto.output.VehicleTypeDTO;
import com.betacom.sb.services.implementation.FuelTypeImpl;
import com.betacom.sb.services.implementation.VehicleImpl;
import com.betacom.sb.services.implementation.VehicleTypeImpl;

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
	
	@Autowired
	private VehicleTypeImpl vehicleTypeImpl;
	
	@Autowired
	private FuelTypeImpl fuelTypeImpl;
	
	@Test
	@Order(1)
	public void createBicylceTest() throws Exception{
		log.debug("createBicylceTest");
		
		fuelTypeImpl.create(FuelTypeReq.builder().fuelType("lpg").build());
		vehicleTypeImpl.create(VehicleTypeReq.builder().vehicleType("bicycle").build());
		
		BicycleReq req = new BicycleReq();
		req.setGearCount(3);
		req.setBrakeType(BrakeTypeDTO.builder().id((long) 2).build());
		req.setSuspensionType(SuspensionTypeDTO.builder().id((long) 2).build());
		req.setIsFoldable(true);
		req.setWheelCount(2);
		req.setColor("Red");
		req.setBrand("brand1");
		req.setProductionYear(2000);
		req.setModel("model3");
		req.setVehicleType(VehicleTypeDTO.builder().id((long) 1).build());
		req.setCategory(CategoryDTO.builder().id((long) 2).build());
		req.setFuelType(FuelTypeDTO.builder().id((long) 1).build());
		
		mockMvc.perform(post("/rest/bicycle/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
	}
	
	@Test
	@Order(2)
	public void createBicylceTestError() throws Exception{
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
	public void listAllBicylce() throws Exception{
		log.debug("listAllBicylce");
		
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
	public void deleteBicylce() throws Exception{
		log.debug("deleteBicylce");
		
		mockMvc.perform(delete("/rest/suspensionType/bicycle/" +  "1"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.msg").exists());  
	}
	
}
