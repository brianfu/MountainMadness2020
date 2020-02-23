package com.sfucsss.mountainmadness2020.bathroom;

import java.util.ArrayList;

public class Checkpoint implements Checkpoint_I {
    private ArrayList<AugmentedPin> pins;

    public Checkpoint() {
        pins = new ArrayList<AugmentedPin>();
    }
    public Checkpoint(ArrayList<AugmentedPin> pins) {
        this.pins = pins;
    }
    @Override
    public String currentWord() {
        String returnString = "";
        for (AugmentedPin x : pins) {
            returnString += x.letter;
        }
        return returnString;
    }
    @Override
    public double totalTime() {
        double returnTime = 0;
        for (int i = 0; i < pins.size() - 1; ++i) {
            returnTime += pins.get(i + 1).timeStamp - pins.get(i).timeStamp;
        }
        return returnTime;
    }
    @Override
    public double score() {
        return currentWord().length();
    }
    @Override
    public boolean contains(Pin pin) {
        for (Pin x : pins) {
            if (pin.equal(x)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean equal(Checkpoint checkpoint) {
        return this.currentWord().equals(checkpoint.currentWord());
    }
    @Override
    public void update(AugmentedPin pin) {
        pins.add(pin);
    }
}
