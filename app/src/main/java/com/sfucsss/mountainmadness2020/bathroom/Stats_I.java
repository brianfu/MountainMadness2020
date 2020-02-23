package com.sfucsss.mountainmadness2020.bathroom;

import java.util.ArrayList;

public interface Stats_I {
    double currentScore();
    double totalTime();
    ArrayList<String> words();
    boolean contains(Checkpoint checkpoint);
    void update(Checkpoint checkpoint);
}
