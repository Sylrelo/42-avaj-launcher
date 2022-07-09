package com.slopez.avaj.exceptions;

public class AircraftCreationError extends Exception {
    public AircraftCreationError(String type) {
        super("Cannot create specified Aircraft type : " + type);
    }
}
