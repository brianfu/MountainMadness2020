package com.sfucsss.mountainmadness2020.bathroom;

import java.util.ArrayList;

public interface GameManager_I {
    // updates the game manager based on the x, y position of the player and timestamp of the game
    public void update(double x, double y, double timeStamp);
    // Return true if the current pattern of string is not in stats and if it is a valid word
    public boolean isValid();
    // This method is used when the player is done with the current string
    public void finishedCurrentString();
    // returns the last letter
    public char lastLetter();
    // returns the current string
    public String currentString();
    // returns the list of strings already used
    public ArrayList<String> allString();
}
