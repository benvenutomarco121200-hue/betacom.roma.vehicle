package com.betacom.sb.dto.output;

import com.betacom.sb.enums.BrakeType;
import com.betacom.sb.enums.SuspensionType;

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
public class BicycleDTO {
    private Long id;
    private Integer gearCount;
    private BrakeType brakeType;
    private SuspensionType suspensionType;
    private Boolean isFoldable;
    
    private VehicleDTO vehicle;
}