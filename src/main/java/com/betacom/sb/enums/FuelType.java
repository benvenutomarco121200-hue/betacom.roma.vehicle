package com.betacom.sb.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

import exceptions.BetacomRomaException;

public enum FuelType {
	LPG, 
	DIESEL, 
	PETROL, 
	HYBRID, 
	ELECTRIC, 
	NONE;
	
	@JsonCreator
    public static FuelType fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null; 
        }
        try {
            return FuelType.valueOf(value.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new BetacomRomaException("The value '" + value + "' is not a valid FuelType. Accepted values: " + Arrays.toString(FuelType.values()));
        }
    }
}
