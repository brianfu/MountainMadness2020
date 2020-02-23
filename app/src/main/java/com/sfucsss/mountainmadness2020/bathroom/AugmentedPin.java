package com.sfucsss.mountainmadness2020.bathroom;

public class AugmentedPin extends Pin {
    public double timeStamp;
    public double distanceStamp;

    public AugmentedPin(double longitude, double latitude,
                        char letter, double timeStamp, double distanceStamp) {
        super(longitude, latitude, letter);
        this.timeStamp = timeStamp;
        this.distanceStamp = distanceStamp;
    }
}
