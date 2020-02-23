package com.sfucsss.mountainmadness2020.bathroom;

import java.util.ArrayList;

public interface MyMap_I {
    public boolean isCloseEnough(double longitude, double latitude);
    public Pin getClosePin(double longitude, double latitude);
    public ArrayList<Pin> locations();
}
