package com.slopez.avaj.tower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.slopez.avaj.exceptions.SimulationError;
import com.slopez.avaj.simulator.Flyable;

public class Tower {

    private List<Flyable> observers;
    private List<Flyable> toRemove;

    public Tower() {
        this.observers = new ArrayList<Flyable>();
        this.toRemove = new ArrayList<Flyable>();
    }

    public void register(Flyable flyable) {
        flyable.printMessage("Registered to WeatherTowder.");
        this.observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        flyable.printMessage("Unregistered of WeatherTowder.");
        this.toRemove.add(flyable);
    }

    protected void conditionsChanged() throws SimulationError {
        this.toRemove.clear();

        for (Flyable flyable : this.observers) {
            try {
                flyable.updateConditions();

                if (flyable.hasLanded()) {
                    this.unregister(flyable);
                    flyable.removeWeatherTower();
                }
            } catch (Exception e) {
                throw new SimulationError(flyable);
            }

        }

        this.observers.removeAll(this.toRemove);
    }

}
