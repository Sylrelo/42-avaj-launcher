package com.slopez.avaj.simulator;

import com.slopez.avaj.Coordinates;
import com.slopez.avaj.tower.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);

        this.type = "JetPlane";
        this.modifiers.put("SUN", new Coordinates(+10, 0, +2));
        this.modifiers.put("RAIN", new Coordinates(0, +5, 0));
        this.modifiers.put("FOG", new Coordinates(0, +1, 0));
        this.modifiers.put("SNOW", new Coordinates(0, 0, -7));
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
            this.printMessage("My blade will melt !");
        if (newWeather == "RAIN")
            this.printMessage("It's rainiiiiiiing men, hallelujah, it's raining meeeen");
        if (newWeather == "FOG")
            this.printMessage("Where the f@$k am I headed ? I can't see anything !");
        if (newWeather == "SNOW")
            this.printMessage("Dang, i'm goona freeze.");
    }
}
