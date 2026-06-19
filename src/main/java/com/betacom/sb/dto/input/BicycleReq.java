package com.betacom.sb.dto.input;

import com.betacom.sb.dto.output.BrakeTypeDTO;
import com.betacom.sb.dto.output.SuspensionTypeDTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BicycleReq extends VehicleReq{

	@NotNull (groups = ValidationGroups.Create.class , message ="marce_invalid")
	@Min(value = 1, groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, message = "marce_invalid")
	@Max(value = 25, groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, message = "marce_invalid")
    private Integer gearCount;
	
	@NotNull (groups = ValidationGroups.Create.class , message ="break_invalid")	
    private BrakeTypeDTO brakeType;
    
    @NotNull (groups = ValidationGroups.Create.class , message ="sos_invalid")	
    private SuspensionTypeDTO suspensionType;
    
    private Boolean isFoldable;

}