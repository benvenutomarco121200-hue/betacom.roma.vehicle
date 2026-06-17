package com.betacom.sb.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

import exceptions.BetacomRomaException;

public enum SuspensionType {
    NONE,
    SPRING,
    AIR;
	
	@JsonCreator
    public static SuspensionType fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null; 
        }
        try {
            return SuspensionType.valueOf(value.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new BetacomRomaException("The value '" + value + "' is not a valid SuspensionType. Accepted values: " + Arrays.toString(SuspensionType.values()));
        }
    }
}
