package com.betacom.sb.dto.input;

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
public class CarReq {
	private Long id;
	private String licensePlate;
	private Integer displacementCc;
	private Integer doorCount;
    private Long vehicleId;
}
