package com.sfucsss.mountainmadness2020.bathroom;

import android.content.Context;

import java.util.ArrayList;

public class GameManager implements GameManager_I{
    public Dict myDict;
    public Stats stat;
    public MyMap myMap;
    public Context context;
    private Checkpoint currentCheckpoint;

    GameManager(Context context) {
        myDict = new Dict(context);
        stat = new Stats();
        myMap = new MyMap(context);
        this.context = context;
        currentCheckpoint = new Checkpoint();
    }


    @Override
    public void update(double x, double y, double timestamp) {
        Pin pinTemp;
        // check if x,y is within the 10m radius (func provided) return boolean
        boolean isCloseEnough = myMap.isCloseEnough(x, y);
        if (isCloseEnough) { // if true: get pin from myMap
            pinTemp = myMap.getClosePin(x, y);
        } else { // if false: return
            return;
        }
        // check if pin is in current checkpoint (use checkpoint contains)
        AugmentedPin augPinTemp = new AugmentedPin(pinTemp.longitude, pinTemp.latitude,
                                                    pinTemp.letter, timestamp);
        boolean alreadyInTemp = currentCheckpoint.contains(pinTemp);
        if (!alreadyInTemp) { // if false: add to temp checkpoint
            currentCheckpoint.update(augPinTemp);
        } else { // if true: return
            return;
        }
    }
    // Return true if the current pattern of string is not in stats and if it is a valid word
    // check if current checkpoint's string is in stats
    // if true
    @Override
    public boolean isValid() {
        if (!stat.contains(currentCheckpoint) && myDict.isValid(currentCheckpoint.currentWord())) {
            return true;
        }
        return false;
    }

    @Override
    public void finishedCurrentString() { // This method is used when the player is done with the current string
        // check if current checkpoint's string is valid
        if (isValid()) {
            stat.update(currentCheckpoint);
        }
        // reset current checkpoint
        currentCheckpoint = new Checkpoint();
    }

    @Override
    public char lastLetter() { // returns the last letter
        String s = currentCheckpoint.currentWord();
        if (s.isEmpty()) {
            return '!';
        }
        return s.charAt(s.length()-1);
    }

    @Override
    public String currentString() { // returns the current string
        return currentCheckpoint.currentWord();
    }

    @Override
    public ArrayList<String> allString() { // returns the list of strings already used
        return stat.words();
    }

    @Override
    public ArrayList<Pin> allPins() {return myMap.locations();}

}