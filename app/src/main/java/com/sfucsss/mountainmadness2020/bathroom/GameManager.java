package com.sfucsss.mountainmadness2020.bathroom;

import java.util.ArrayList;

public class GameManager implements GameManager_I{
    public Dict myDict;
    public Stats stat;
    public MyMap myMap;
    private Checkpoint currentCheckpoint;

    @Override
    public void update(double x, double y, double timestamp) {
        Pin pin_temp;
        Checkpoint checkpoint_temp = new Checkpoint();
        // check if x,y is within the 10m radius (func provided) return boolean
        boolean flag = myMap.isCloseEnough(x, y);
        if (flag) { // if true: get pin from myMap
            pin_temp = myMap.getClosePin(x, y);
        } else { // if false: return
            return;
        }
        // check if pin is in current checkpoint (use checkpoint contains)
        AugmentedPin AugPin_temp = new AugmentedPin(pin_temp.longitude, pin_temp.latitude,
                                                    pin_temp.letter, timestamp);
        flag = checkpoint_temp.contains(pin_temp);
        if (!flag) { // if false: add to temp checkpoint
            checkpoint_temp.update(AugPin_temp);
        } else { // if true: return
            return;
        }
        // add to temp checkpoint
        // when done, check if string is in stats
        flag = stat.contains(checkpoint_temp);
        if (flag) { // if false: add to stats (use update in stats)
            stat.update(checkpoint_temp);
        } else { // if true: return
            return;
        }
        return;;
    }

    @Override
    public boolean isValid() { // Return true if the current pattern of string is not in stats and if it is a valid word
        // check if current checkpoint's string is in stats

    }

    @Override
    public void finishedCurrentString() { // This method is used when the player is done with the current string

    }

    @Override
    public char lastLetter() { // returns the last letter

    }

    @Override
    public String lastString() { // returns the last string


    }

    @Override
    public ArrayList<String> allString() { // returns the list of strings already used

    }

}