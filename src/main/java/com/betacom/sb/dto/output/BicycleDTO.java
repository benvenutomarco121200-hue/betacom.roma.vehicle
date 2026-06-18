package com.betacom.sb.dto.output;

import com.betacom.sb.models.BrakeType;
import com.betacom.sb.models.SuspensionType;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BicycleDTO {
    private Long id;
    private Integer gearCount;
    private String brakeType;
    private String suspensionType;
    private Boolean isFoldable;
    
    private VehicleDTO vehicle;
}