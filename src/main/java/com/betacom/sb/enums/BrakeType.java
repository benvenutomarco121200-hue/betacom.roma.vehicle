package com.betacom.sb.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

import exceptions.BetacomRomaException;

public enum BrakeType {
    DRUM,
    DISC;
	
	@JsonCreator
    public static BrakeType fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null; 
        }
        try {
            return BrakeType.valueOf(value.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new BetacomRomaException("The value '" + value + "' is not a valid BrakeType. Accepted values: " + Arrays.toString(BrakeType.values()));
        }
    }
}
