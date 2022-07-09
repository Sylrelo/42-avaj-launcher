package com.slopez.avaj.tower;

import com.slopez.avaj.Coordinates;
import com.slopez.avaj.exceptions.SimulationError;
import com.slopez.avaj.weather.WeatherProvider;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    public void changeWeather() throws SimulationError {
        this.conditionsChanged();
    };
}
