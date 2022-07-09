package com.slopez.avaj.exceptions;

import com.slopez.avaj.simulator.Flyable;

public class SimulationError extends Exception {
    public SimulationError(Flyable flyable) {
        super("An error occured during simulation.");
        System.out.println(flyable.toString());
    }
}
