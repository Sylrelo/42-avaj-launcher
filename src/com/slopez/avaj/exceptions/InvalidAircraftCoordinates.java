package com.slopez.avaj.exceptions;

public class InvalidAircraftCoordinates extends Exception {
    public InvalidAircraftCoordinates() {
        super("Aircraft coordinates are invalid. (Must be positive)");
    }
}
