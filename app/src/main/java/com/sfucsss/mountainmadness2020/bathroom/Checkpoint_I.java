package com.sfucsss.mountainmadness2020.bathroom;

public interface Checkpoint_I {
    public String currentWord();
    public double totalTime();
    public double score();
    public boolean contains(Pin pin);
    public boolean equal(Checkpoint checkpoint);
    public void update(AugmentedPin pin);
}
