package com.betacom.sb.dto.output;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BicycleDTO {
	
    private Long id;
    private Integer gearCount;
    private BrakeTypeDTO brakeType;
    private SuspensionTypeDTO suspensionType;
    private Boolean isFoldable;
    
    private VehicleDTO vehicle;
}