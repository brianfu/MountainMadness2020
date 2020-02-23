package com.sfucsss.mountainmadness2020.bathroom;

public class Pin {
    public double longitude;
    public double  latitude;
    public char letter;

    public Pin(double longitude, double latitude, char letter) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.letter = letter;
    }
    public boolean equal(Pin pin) {
        return pin.longitude == this.longitude &&
                pin.latitude == this.latitude;
    }
}