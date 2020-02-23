package com.sfucsss.mountainmadness2020.bathroom;

public interface Checkpoint_I {
    String currentWord();
    double totalTime();
    double score();
    boolean contains(Pin pin);
    boolean equal(Checkpoint checkpoint);
    void update(AugmentedPin pin);
}
