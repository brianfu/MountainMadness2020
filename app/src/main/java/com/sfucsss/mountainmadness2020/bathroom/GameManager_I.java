package com.sfucsss.mountainmadness2020.bathroom;

import java.util.ArrayList;

public interface GameManager_I {
    void update(double x, double y, double timestamp);
    // Return true if the current pattern of string is not in stats and if it is a valid word
    boolean isValid();
    // This method is used when the player is done with the current string
    void finishedCurrentString(); //also adds the str to the list
    // returns the last letter
    char lastLetter(); //Why do I need this? Doesn't gameManager handle all of where the points are anyway?
    // returns the current string
    String currentString();
    // returns the list of strings already used
    ArrayList<String> allString();
}
