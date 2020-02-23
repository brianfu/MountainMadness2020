package com.sfucsss.mountainmadness2020.bathroom;

import java.util.ArrayList;

public interface Stats_I {
    public double currentScore();
    public double totalTime();
    public ArrayList<String> words();
    public boolean contains(Checkpoint checkpoint);
    public void update(Checkpoint checkpoint);
}
