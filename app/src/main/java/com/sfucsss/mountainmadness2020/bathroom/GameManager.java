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
        Pin pin_temp;
        Checkpoint checkpoint_temp = new Checkpoint();
        // check if x,y is within the 10m radius (func provided) return boolean
        boolean isCloseEnough = myMap.isCloseEnough(x, y);
        if (isCloseEnough) { // if true: get pin from myMap
            pin_temp = myMap.getClosePin(x, y);
        } else { // if false: return
            return;
        }
        // check if pin is in current checkpoint (use checkpoint contains)
        AugmentedPin AugPin_temp = new AugmentedPin(pin_temp.longitude, pin_temp.latitude,
                                                    pin_temp.letter, timestamp);
        isCloseEnough = checkpoint_temp.contains(pin_temp);
        if (!isCloseEnough) { // if false: add to temp checkpoint
            checkpoint_temp.update(AugPin_temp);
        } else { // if true: return
            return;
        }
        // add to temp checkpoint
        // when done, check if string is in stats
        isCloseEnough = stat.contains(checkpoint_temp);
        if (isCloseEnough) { // if false: add to stats (use update in stats)
            stat.update(checkpoint_temp);
        } else { // if true: return
            return;
        }
        return;
    }

    @Override
    public boolean isValid() { // Return true if the current pattern of string is not in stats and if it is a valid word
        // check if current checkpoint's string is in stats
        // if true
        if (stat.contains(currentCheckpoint)) {
            if (myDict.isValid(currentCheckpoint.currentWord())) {
                return true;
            }
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