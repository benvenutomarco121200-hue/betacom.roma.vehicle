package com.betacom.sb.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeleteFinalTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void deleteCarTest() throws Exception {
        log.debug("deleteCarTest");

        mockMvc.perform(delete("/rest/car/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").exists());
    }
    
    @Test
    @Order(2)
    public void deleteMotorcycleTest() throws Exception {
    	log.debug("deleteMotorcycleTest");
    	
    	mockMvc.perform(delete("/rest/motorcycle/delete/1"))
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$.msg").exists());
    }
    
    @Test
	@Order(3)
	public void deleteBicylceTest() throws Exception{
		log.debug("deleteBicylceTest");
		
		mockMvc.perform(delete("/rest/bicycle/delete/" +  "1"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.msg").exists());  
	}
    
    @Test
    @Order(4)
    public void deleteVehicleTypeTest() throws Exception {
        log.debug("deleteVehicleTypeTest");

        mockMvc.perform(delete("/rest/vehicleType/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").exists());
    }

    @Test
    @Order(5)
    public void deleteCategoryTest() throws Exception {
        log.debug("deleteCategoryTest");

        mockMvc.perform(delete("/rest/category/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").exists());
    }

    @Test
    @Order(6)
    public void deleteFuelTypeTest() throws Exception {
        log.debug("deleteFuelTypeTest");

        mockMvc.perform(delete("/rest/fuelType/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").exists());
    }
    
    @Test
	@Order(7)
	public void deleteBrakeTypeTest() throws Exception{
		log.debug("deleteBrakeTypeTest");
		
		mockMvc.perform(delete("/rest/brakeType/delete/" +  "1"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.msg").exists());  
	}
    
    @Test
	@Order(8)
	public void deleteSuspensionTypeTest() throws Exception{
		log.debug("deleteSuspensionTypeTest");
		
		mockMvc.perform(delete("/rest/suspensionType/delete/" +  "1"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.msg").exists());  
	}
}