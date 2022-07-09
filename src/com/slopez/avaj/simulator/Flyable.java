package com.slopez.avaj.simulator;

import com.slopez.avaj.tower.WeatherTower;

public interface Flyable {
    public void updateConditions();

    public void registerTower(WeatherTower weatherTower);

    public String getName();

    public Boolean hasLanded();

    public void printMessage(String message);

    public void removeWeatherTower();
}
