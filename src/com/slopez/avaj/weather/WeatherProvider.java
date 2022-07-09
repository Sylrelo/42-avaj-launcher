package com.slopez.avaj.weather;

import java.util.Random;

import com.slopez.avaj.Coordinates;

public class WeatherProvider {
    static private WeatherProvider weatherProvider;
    static private String[] weather = { "RAIN", "FOG", "SUN", "SNOW" };

    private WeatherProvider() {
        System.out.println("Creating WeatherProvider.");
    }

    static public WeatherProvider getProvider() {
        if (WeatherProvider.weatherProvider == null) {
            WeatherProvider.weatherProvider = new WeatherProvider();
        }
        return WeatherProvider.weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return WeatherProvider.weather[getRandomWeather(coordinates)];
    }

    private int getRandomWeather(Coordinates coordinates) {

        Random rnd = new Random();

        rnd.setSeed(coordinates.getLongitude() + coordinates.getLatitude() * coordinates.getHeight());

        return rnd.nextInt(WeatherProvider.weather.length);

    }
}
