package com.sfucsss.mountainmadness2020.bathroom;

import com.sfucsss.mountainmadness2020.bathroom.Checkpoint;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Stats {
    private ArrayList<Checkpoint> checkpoints;

    public Stats() {
        checkpoints = new ArrayList<>();
    }
    public Stats(ArrayList<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }
    public double currentScore() {
        double returnScore = 0;
        for (Checkpoint x : checkpoints) {
            returnScore += x.score();
        }
        return returnScore;
    }
    public double totalTime() {
        double returnValue = 0;
        for (Checkpoint x : checkpoints) {
            returnValue += x.totalTime();
        }
        return returnValue;
    }
    public double totalDistance() {
        double returnValue = 0;
        for (Checkpoint x : checkpoints) {
            returnValue += x.totalDistance();
        }
        return returnValue;
    }
    public ArrayList<String> words() {
        ArrayList<String> returnWords = new ArrayList<>();
        for (Checkpoint x : checkpoints) {
            returnWords.add(x.currentWord());
        }
        return returnWords;
    }
    public boolean search(Checkpoint checkpoint) {
        return words().contains(checkpoint.currentWord());
    }
    public void update(Checkpoint checkpoint) {
        checkpoints.add(checkpoint);
    }
}
