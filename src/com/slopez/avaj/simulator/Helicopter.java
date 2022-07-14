package com.slopez.avaj.simulator;

import com.slopez.avaj.Coordinates;
import com.slopez.avaj.tower.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);

        this.type = "Helicopter";
        this.modifiers.put("SUN", new Coordinates(+10, 0, +2));
        this.modifiers.put("RAIN", new Coordinates(0, +5, 0));
        this.modifiers.put("FOG", new Coordinates(0, +1, 0));
        this.modifiers.put("SNOW", new Coordinates(0, 0, -12));
    }

    @Override
    public void updateConditions() {
        String currentWeather = this.weatherTower.getWeather(coordinates);
        this.coordinates.update(this.modifiers.get(currentWeather));

        this.weatherChangeMessage(currentWeather);
        if (this.hasLanded()) {
            this.printMessage("Landed at " + this.coordinates.getStringCoordinates());
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
    }

    @Override
    public void removeWeatherTower() {
        this.weatherTower = null;
    }

    private void weatherChangeMessage(String oldWeather) {
        String newWeather = this.weatherTower.getWeather(this.coordinates);

        if (oldWeather == newWeather) {
            return;
        }

        if (newWeather == "SUN")
            this.printMessage("I can't see anything");
        if (newWeather == "RAIN")
            this.printMessage("I'm scared of the rain");
        if (newWeather == "FOG")
            this.printMessage("I'm gonna crash because I can't see anything");
        if (newWeather == "SNOW")
            this.printMessage("I'm now an ice cube");
    }
}
