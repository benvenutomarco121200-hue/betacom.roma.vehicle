package com.betacom.sb.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.BicycleReq;
import com.betacom.sb.dto.output.BicycleDTO;
import com.betacom.sb.enums.VehicleType;
import com.betacom.sb.mapping.BicycleMap;
import com.betacom.sb.models.Bicycle;
import com.betacom.sb.models.Vehicle;
import com.betacom.sb.repositories.IBicycleRepository;
import com.betacom.sb.repositories.IVehicleRepository;
import com.betacom.sb.services.interfaces.IBicycleServices;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BicycleImpl implements IBicycleServices {

    private final IBicycleRepository repoBicycle;
    private final IVehicleRepository repoVehicle;

    @Override
    public BicycleDTO getById(Long id) throws Exception {
        log.debug("get at id {}", id);
        Bicycle bicycle = repoBicycle.findById(id)
                .orElseThrow(() -> new Exception("bicycle not found"));
        return BicycleMap.buildBicycleDTO(bicycle);
    }

    @Override
    public List<BicycleDTO> list() throws Exception {
        log.debug("list");
        List<Bicycle> bicycles = repoBicycle.findAll();
        return BicycleMap.buildListBicycleDTO(bicycles);
    }

    @Transactional
    @Override
    public void create(BicycleReq req) throws Exception {
        log.debug("create {}", req);

        if (req.getGearCount() == null) {
            throw new Exception("gear count cannot be null");
        }
        if (req.getBrakeType() == null) {
            throw new Exception("brake type cannot be null");
        }
        if (req.getSuspensionType() == null) {
            throw new Exception("suspension type cannot be null");
        }
        if (req.getIsFoldable() == null) {
            throw new Exception("is foldable cannot be null");
        }

        Bicycle bicycle = new Bicycle();
        bicycle.setGearCount(req.getGearCount());
        bicycle.setBrakeType(req.getBrakeType());
        bicycle.setSuspensionType(req.getSuspensionType());
        bicycle.setIsFoldable(req.getIsFoldable());

        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(req.getBrand());
        vehicle.setModel(req.getModel());
        vehicle.setColor(req.getColor());
        vehicle.setWheelCount(req.getWheelCount());
        vehicle.setProductionYear(req.getProductionYear());
        vehicle.setFuelType(req.getFuelType());
        vehicle.setCategory(req.getCategory());
        vehicle.setVehicleType(VehicleType.BICYCLE);

        bicycle.setVehicle(vehicle);
        vehicle.setBicycle(bicycle);

        repoVehicle.save(vehicle);
        repoBicycle.save(bicycle);
    }

    @Transactional
    @Override
    public void update(BicycleReq req) throws Exception {
        log.debug("update {}", req);

        if (req.getId() == null || req.getId() == 0) {
            throw new Exception("Bicycle ID is required for update");
        }

        Bicycle bicycle = repoBicycle.findById(req.getId())
                .orElseThrow(() -> new Exception("Bicycle not found with id: " + req.getId()));

        bicycle.setGearCount(req.getGearCount());
        bicycle.setBrakeType(req.getBrakeType());
        bicycle.setSuspensionType(req.getSuspensionType());
        bicycle.setIsFoldable(req.getIsFoldable());

        Vehicle vehicle = bicycle.getVehicle();
        if (vehicle == null) {
            throw new Exception("Data integrity error: Linked vehicle not found for this bicycle");
        }

        vehicle.setBrand(req.getBrand());
        vehicle.setModel(req.getModel());
        vehicle.setColor(req.getColor());
        vehicle.setWheelCount(req.getWheelCount());
        vehicle.setProductionYear(req.getProductionYear());
        vehicle.setFuelType(req.getFuelType());
        vehicle.setCategory(req.getCategory());

        repoBicycle.save(bicycle);
    }

    @Transactional
    @Override
    public void delete(Long id) throws Exception {
        log.debug("delete {}", id);
        Bicycle bicycle = repoBicycle.findById(id)
                .orElseThrow(() -> new Exception("id not valid"));
        repoBicycle.delete(bicycle);
    }
}