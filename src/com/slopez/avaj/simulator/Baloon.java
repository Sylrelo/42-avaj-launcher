package com.slopez.avaj.simulator;

import com.slopez.avaj.Coordinates;
import com.slopez.avaj.tower.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);

        this.type = "Baloon";
        this.modifiers.put("SUN", new Coordinates(+2, 0, +4));
        this.modifiers.put("RAIN", new Coordinates(0, 0, -5));
        this.modifiers.put("FOG", new Coordinates(0, 0, -3));
        this.modifiers.put("SNOW", new Coordinates(0, 0, -15));
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
            this.printMessage("I'm gonna pop.");
        if (newWeather == "RAIN")
            this.printMessage("Water slowly mouilling me.");
        if (newWeather == "FOG")
            this.printMessage("I'm a big blur.");
        if (newWeather == "SNOW")
            this.printMessage("Death by snow snow.");
    }
}
