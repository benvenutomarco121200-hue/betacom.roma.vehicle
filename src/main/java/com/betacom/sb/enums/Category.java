package com.betacom.sb.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

import exceptions.BetacomRomaException;

public enum Category {
    STREET, 
    SUV, 
    OFF_ROAD, 
    SEDAN, 
    MOTOCROSS,
    ENDURO, 
    RACING,
    MOUNTAIN_BIKE,
    SCOOTER,
    PICK_UP;
	
	@JsonCreator
    public static Category fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null; 
        }
        try {
            return Category.valueOf(value.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new BetacomRomaException("The value '" + value + "' is not a valid Category. Accepted values: " + Arrays.toString(Category.values()));
        }
    }
}
