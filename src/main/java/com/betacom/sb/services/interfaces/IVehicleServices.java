package com.betacom.sb.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.VehicleReq;
import com.betacom.sb.dto.output.VehicleDTO;
import com.betacom.sb.models.Vehicle;

@Service
public interface IVehicleServices {
	VehicleDTO getById(Long id) throws Exception;
	List<VehicleDTO> listAll() throws Exception;
	List<VehicleDTO> find(Long id, String vehicleType, String category, String fuelType, String color, String brand, String model, String licensePlate, Integer doorCount);
	
	void update(VehicleReq req, Vehicle vehicle) throws Exception;
	Long create(VehicleReq req);
}
