package com.betacom.sb.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.BicycleReq;
import com.betacom.sb.dto.output.BicycleDTO;
import com.betacom.sb.mapping.BicycleMap;
import com.betacom.sb.models.Bicycle;
import com.betacom.sb.models.BrakeType;
import com.betacom.sb.models.SuspensionType;
import com.betacom.sb.models.Vehicle;
import com.betacom.sb.repositories.IBicycleRepository;
import com.betacom.sb.repositories.IBrakeTypeRepository;
import com.betacom.sb.repositories.ISuspensionTypeRepository;
import com.betacom.sb.repositories.IVehicleRepository;
import com.betacom.sb.services.interfaces.IBicycleServices;
import com.betacom.sb.services.interfaces.IVehicleServices;

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
    private final IVehicleServices servVehicle;
    
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
		Bicycle bicycle = new Bicycle();
		Vehicle vehicle = repoVehicle.findById(servVehicle.create(req))
				.orElseThrow(() -> new BetacomRomaException("vehicle not create"));
		
		if (req.getGearCount() == null) {
            throw new BetacomRomaException("gear count cannot be null");
        }
		bicycle.setGearCount(req.getGearCount());
        
        if (req.getIsFoldable() == null) {
            throw new BetacomRomaException("is foldable cannot be null");
        }
        bicycle.setIsFoldable(req.getIsFoldable());
        
        SuspensionType suspensionType = repoSuspension.findById(
                Optional.ofNullable(req.getSuspensionType())
                        .orElseThrow(() -> new BetacomRomaException("Suspension type cannot be null"))
                        .getId())
                .orElseThrow(() -> new BetacomRomaException("suspension_type_invalid"));

        bicycle.setSuspensionType(suspensionType);

        BrakeType brakeType = repoBrake.findById(
                Optional.ofNullable(req.getBrakeType())
                        .orElseThrow(() -> new BetacomRomaException("Brake type cannot be null"))
                        .getId())
                .orElseThrow(() -> new BetacomRomaException("brake_type_invalid"));

        bicycle.setBrakeType(brakeType);
		
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

	    Vehicle vehicle = bicycle.getVehicle();

	    if (vehicle == null) {
	        throw new BetacomRomaException("Vehicle linked to bicycle not found");
	    }
	    
	    if (req.getBrakeType() != null) {
	        BrakeType brakeType = repoBrake.findById(req.getBrakeType().getId())
	                .orElseThrow(() -> new BetacomRomaException("brake_type_invalid"));
	        bicycle.setBrakeType(brakeType);
	    }

	    if (req.getSuspensionType() != null) {
	        SuspensionType suspensionType = repoSuspension.findById(req.getSuspensionType().getId())
	                .orElseThrow(() -> new BetacomRomaException("suspension_type_invalid"));
	        bicycle.setSuspensionType(suspensionType);
	    }
	    
	    Optional.ofNullable(req.getIsFoldable()).ifPresent(bicycle::setIsFoldable);
	    Optional.ofNullable(req.getGearCount()).ifPresent(bicycle::setGearCount);

	    //necessaria per aggiornare anche il veicolo
	    servVehicle.update(req, vehicle);

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