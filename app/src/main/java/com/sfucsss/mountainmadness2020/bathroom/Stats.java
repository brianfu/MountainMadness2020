package com.sfucsss.mountainmadness2020.bathroom;

import com.sfucsss.mountainmadness2020.bathroom.Checkpoint;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Stats implements Stats_I{
    private ArrayList<Checkpoint> checkpoints;

    public Stats() {
        checkpoints = new ArrayList<>();
    }
    public Stats(ArrayList<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }
    @Override
    public double currentScore() {
        double returnScore = 0;
        for (Checkpoint x : checkpoints) {
            returnScore += x.score();
        }
        return returnScore;
    }
    @Override
    public double totalTime() {
        double returnValue = 0;
        for (Checkpoint x : checkpoints) {
            returnValue += x.totalTime();
        }
        return returnValue;
    }
    @Override
    public ArrayList<String> words() {
        ArrayList<String> returnWords = new ArrayList<>();
        for (Checkpoint x : checkpoints) {
            returnWords.add(x.currentWord());
        }
        return returnWords;
    }
    @Override
    public boolean contains(Checkpoint checkpoint) {
        return words().contains(checkpoint.currentWord());
    }
    @Override
    public void update(Checkpoint checkpoint) {
        checkpoints.add(checkpoint);
    }
}
