package com.sfucsss.mountainmadness2020.bathroom;

public class AugmentedPin extends Pin {
    public double timeStamp;

    public AugmentedPin(double longitude, double latitude,
                        char letter, double timeStamp) {
        super(longitude, latitude, letter);
        this.timeStamp = timeStamp;
    }
}
