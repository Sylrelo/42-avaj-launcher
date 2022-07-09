package com.slopez.avaj;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    public Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public int getLongitude() {
        return this.longitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public int getHeight() {
        return this.height;
    }

    public void update(Coordinates coordinates) {
        this.latitude += coordinates.getLatitude();
        this.longitude += coordinates.getLongitude();
        this.height += coordinates.getHeight();

        this.height = Math.max(this.height, 0);
    }

    public Coordinates clone() {
        return new Coordinates(this.longitude, this.latitude, this.height);
    }

    public String getStringCoordinates() {
        return String.format("Lat: %d, Lon: %d, Height: %d", this.latitude, this.longitude, this.height);
    }

}
