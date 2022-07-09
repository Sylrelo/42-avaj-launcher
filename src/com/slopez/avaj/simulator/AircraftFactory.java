package com.slopez.avaj.simulator;

import com.slopez.avaj.Coordinates;
import com.slopez.avaj.exceptions.AircraftCreationError;
import com.slopez.avaj.exceptions.InvalidAircraft;
import com.slopez.avaj.exceptions.InvalidAircraftCoordinates;

public class AircraftFactory {

    static public Flyable newAircraft(String type, String name, int longitude, int latitude, int height)
            throws InvalidAircraft, InvalidAircraftCoordinates, AircraftCreationError {

        if (type.isEmpty() || name.isEmpty()) {
            throw new InvalidAircraft("Type and Name must be set.");
        }

        if (longitude < 0 || latitude < 0 || height < 0) {
            throw new InvalidAircraftCoordinates();
        }

        if (height >= 100) {
            height = 100;
        }

        type = type.toLowerCase();

        try {

            switch (type) {
                case "jetplane":
                    return new JetPlane(name, new Coordinates(longitude, latitude, height));
                case "baloon":
                    return new Baloon(name, new Coordinates(longitude, latitude, height));
                case "helicopter":
                    return new Helicopter(name, new Coordinates(longitude, latitude, height));
                default:
                    throw new InvalidAircraft("Aircraft type does not exists.");
            }
        } catch (InvalidAircraft e) {
            throw new InvalidAircraft(e.getMessage());
        } catch (Exception e) {
            throw new AircraftCreationError(type);
        }

    }
}
