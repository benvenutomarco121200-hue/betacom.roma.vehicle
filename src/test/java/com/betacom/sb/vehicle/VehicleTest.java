package com.betacom.sb.vehicle;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.betacom.sb.dto.output.VehicleDTO;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VehicleTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    public void listAllVehicleTest() throws Exception {
        log.debug("listAllVehicleTest");

        MvcResult result = mockMvc.perform(get("/rest/vehicle/listAll"))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        List<VehicleDTO> vehicles = objectMapper.readValue(
                json,
                new TypeReference<List<VehicleDTO>>() {}
        );

        assertFalse(vehicles.isEmpty());

        VehicleDTO vehicle = vehicles.stream()
                .filter(v -> "BMW".equalsIgnoreCase(v.getBrand()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Vehicle BMW non trovato"));

        log.debug("Vehicle trovato: {}", vehicle);
    }

    @Test
    @Order(2)
    public void listVehicleByBrandTest() throws Exception {
        log.debug("listVehicleByBrandTest");

        mockMvc.perform(get("/rest/vehicle/list")
                .param("brand", "BMW"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void listVehicleByModelTest() throws Exception {
        log.debug("listVehicleByModelTest");

        mockMvc.perform(get("/rest/vehicle/list")
                .param("model", "M3"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void listVehicleByLicensePlateTest() throws Exception {
        log.debug("listVehicleByLicensePlateTest");

        mockMvc.perform(get("/rest/vehicle/list")
                .param("licensePlate", "ZZ999ZZ"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void getVehicleByIdTest() throws Exception {
        log.debug("getVehicleByIdTest");

        mockMvc.perform(get("/rest/vehicle/getById")
                .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(6)
    public void getVehicleByIdErrorTest() throws Exception {
        log.debug("getVehicleByIdErrorTest");

        mockMvc.perform(get("/rest/vehicle/getById")
                .param("id", "99999"))
                .andExpect(status().isBadRequest());
    }

    
}