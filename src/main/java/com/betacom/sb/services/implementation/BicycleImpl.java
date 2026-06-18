package com.betacom.sb.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.BicycleReq;
import com.betacom.sb.dto.output.BicycleDTO;
import com.betacom.sb.mapping.BicycleMap;
import com.betacom.sb.models.Bicycle;
import com.betacom.sb.models.BrakeType;
import com.betacom.sb.models.Category;
import com.betacom.sb.models.FuelType;
import com.betacom.sb.models.SuspensionType;
import com.betacom.sb.models.Vehicle;
import com.betacom.sb.models.VehicleType;
import com.betacom.sb.repositories.IBicycleRepository;
import com.betacom.sb.repositories.IBrakeTypeRepository;
import com.betacom.sb.repositories.ICategoryRepository;
import com.betacom.sb.repositories.IFuelTypeRepository;
import com.betacom.sb.repositories.ISuspensionTypeRepository;
import com.betacom.sb.repositories.IVehicleRepository;
import com.betacom.sb.repositories.IVehicleTypeRepository;
import com.betacom.sb.services.interfaces.IBicycleServices;
import com.betacom.sb.utils.Utils;

import exceptions.BetacomRomaException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BicycleImpl implements IBicycleServices {

    private final IBicycleRepository repoBicycle;
    private final IVehicleRepository repoVehicle;
    private final ICategoryRepository repoCategory;
    private final IFuelTypeRepository repoFuel;
    private final IVehicleTypeRepository repoVehicleType;
    private final IBrakeTypeRepository repoBrake;
    private final ISuspensionTypeRepository repoSuspension;

    @Override
    public BicycleDTO getById(Long id) throws Exception {
        log.debug("get at id {}", id);
        Bicycle bicycle = repoBicycle.findById(id)
                .orElseThrow(() -> new BetacomRomaException("bicycle not found"));
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
            throw new BetacomRomaException("gear count cannot be null");
        }
        if (req.getIsFoldable() == null) {
            throw new BetacomRomaException("is foldable cannot be null");
        }

        if (req.getCategory() == null) {
            throw new BetacomRomaException("Category cannot be null");
        }
        Category category = repoCategory.findByCategoryIgnoreCase(req.getCategory().trim())
                .orElseThrow(() -> new BetacomRomaException("The value '" + req.getCategory() + "' is not a valid Category."));

        FuelType fuelType = repoFuel.findByFuelIgnoreCase("NONE")
                .orElseThrow(() -> new BetacomRomaException("FuelType 'NONE' not found in database."));

        VehicleType vehicleType = repoVehicleType.findByVehicleIgnoreCase("BICYCLE")
                .orElseThrow(() -> new BetacomRomaException("VehicleType 'BICYCLE' not found in database."));

        if (req.getBrakeType() == null) {
            throw new BetacomRomaException("brake type cannot be null");
        }
        BrakeType brakeType = repoBrake.findByBrakeIgnoreCase(req.getBrakeType().trim())
                .orElseThrow(() -> new BetacomRomaException("The value '" + req.getBrakeType() + "' is not a valid Brake Type."));

        if (req.getSuspensionType() == null) {
            throw new BetacomRomaException("suspension type cannot be null");
        }
        SuspensionType suspensionType = repoSuspension.findBySuspensionIgnoreCase(req.getSuspensionType().trim())
                .orElseThrow(() -> new BetacomRomaException("The value '" + req.getSuspensionType() + "' is not a valid Suspension Type."));

        Bicycle bicycle = new Bicycle();
        bicycle.setGearCount(req.getGearCount());
        bicycle.setIsFoldable(req.getIsFoldable());

        bicycle.setBrakeType(brakeType);
        bicycle.setSuspensionType(suspensionType);
        
        Vehicle vehicle = Utils.checkVehicleCreate(
                req.getBrand(), 
                req.getModel(), 
                req.getColor(), 
                req.getWheelCount(), 
                req.getProductionYear(), 
                fuelType, 
                category, 
                vehicleType
        );

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
            throw new BetacomRomaException("Bicycle ID is required for update");
        }

        Bicycle bicycle = repoBicycle.findById(req.getId())
                .orElseThrow(() -> new BetacomRomaException("Bicycle not found with id: " + req.getId()));
        
        Optional.ofNullable(req.getGearCount()).ifPresent(bicycle::setGearCount);
        Optional.ofNullable(req.getIsFoldable()).ifPresent(bicycle::setIsFoldable);
        
        if (req.getBrakeType() != null) {
            BrakeType brakeType = repoBrake.findByBrakeIgnoreCase(req.getBrakeType().trim())
                    .orElseThrow(() -> new BetacomRomaException("The value '" + req.getBrakeType() + "' is not a valid Brake Type."));
            bicycle.setBrakeType(brakeType);
        }

        if (req.getSuspensionType() != null) {
            SuspensionType suspensionType = repoSuspension.findBySuspensionIgnoreCase(req.getSuspensionType().trim())
                    .orElseThrow(() -> new BetacomRomaException("The value '" + req.getSuspensionType() + "' is not a valid Suspension Type."));
            bicycle.setSuspensionType(suspensionType);
        }

        Category category = null;
        if (req.getCategory() != null) {
            category = repoCategory.findByCategoryIgnoreCase(req.getCategory().trim())
                    .orElseThrow(() -> new BetacomRomaException("The value '" + req.getCategory() + "' is not a valid Category."));
        }

        FuelType fuelType = null;
        if (req.getFuelType() != null) {
            fuelType = repoFuel.findByFuelIgnoreCase(req.getFuelType().trim())
                    .orElseThrow(() -> new BetacomRomaException("The value '" + req.getFuelType() + "' is not a valid FuelType."));
        }

        Utils.checkVehicleUpdate(
                bicycle.getVehicle(), 
                req.getBrand(), 
                req.getModel(), 
                req.getColor(), 
                req.getWheelCount(), 
                req.getProductionYear(), 
                fuelType,
                category
        );

        repoBicycle.save(bicycle);
    }

    @Transactional
    @Override
    public void delete(Long id) throws Exception {
        log.debug("delete {}", id);
        Bicycle bicycle = repoBicycle.findById(id)
                .orElseThrow(() -> new BetacomRomaException("id not valid"));
        repoBicycle.delete(bicycle);
    }
}