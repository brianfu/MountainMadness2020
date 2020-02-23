package com.sfucsss.mountainmadness2020.bathroom;

import java.util.ArrayList;

public interface MyMap_I {
    boolean isCloseEnough(double longitude, double latitude);
    Pin getClosePin(double longitude, double latitude);
    ArrayList<Pin> locations();
}
