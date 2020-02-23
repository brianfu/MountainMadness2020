package com.sfucsss.mountainmadness2020.bathroom;

import java.util.ArrayList;

public class Checkpoint {
    private ArrayList<AugmentedPin> pins;

    public Checkpoint() {
        pins = new ArrayList<AugmentedPin>();
    }
    public Checkpoint(ArrayList<AugmentedPin> pins) {
        this.pins = pins;
    }
    public String currentWord() {
        String returnString = "";
        for (AugmentedPin x : pins) {
            returnString += x.letter;
        }
        return returnString;
    }
    public double totalTime() {
        double returnTime = 0;
        for (int i = 0; i < pins.size() - 1; ++i) {
            returnTime += pins.get(i + 1).timeStamp - pins.get(i).timeStamp;
        }
        return returnTime;
    }
    public double totalDistance() {
        double returnDistance = 0;
        for (int i = 0; i < pins.size() - 1; ++i) {
            returnDistance += pins.get(i + 1).distanceStamp - pins.get(i).distanceStamp;
        }
        return returnDistance;
    }
    public double score() {
        return currentWord().length();
    }
    public boolean contains(Pin pin) {
        for (Pin x : pins) {
            if (pin.equal(x)) {
                return true;
            }
        }
        return false;
    }
    public boolean equal(Checkpoint checkpoint) {
        return this.currentWord().equals(checkpoint.currentWord());
    }
    public void update(AugmentedPin pin) {
        pins.add(pin);
    }
}
