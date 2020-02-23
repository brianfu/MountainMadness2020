package com.sfucsss.mountainmadness2020.bathroom;

import java.util.ArrayList;
import java.io.*;
import java.lang.String;
import java.lang.Math.*;

public class MyMap implements MyMap_I{

    private int s1 = 0;
    private int s2 = 11;
    private int s3 = 23;
    private ArrayList<Pin> pins;

    public MyMap() {
        this.s1 = 0;
        this.s2 = 11;
        this.s3 = 23;
        File file = new File("/pins.txt");
        FileReader fr;
        try {
            fr = new FileReader(file);
        } catch(FileNotFoundException f) {
            return;
        }
        BufferedReader br = new BufferedReader(fr);
        pins = new ArrayList<Pin>();
        String st;
        do {
            try {
                st = br.readLine();
            } catch(IOException s) {
                return;
            }
            double longitude = Double.parseDouble(st.substring(s1, st.indexOf(st, s1)));
            double latitude = Double.parseDouble(st.substring(s2, st.indexOf(st, s2)));
            char letter = st.charAt(s3);
            Pin p = new Pin(longitude, latitude, letter);
            pins.add(p);
        } while (st != null);
    }
    // creates array of possible pin locations
    @Override
    public ArrayList<Pin> locations() {
        return pins;
    }

    // checks if current location is close enough to pin in locations
    @Override
    public boolean isCloseEnough(double longitude, double latitude) {
        double radiusErr = 0.0001;
        for (Pin pin : locations()){
            double dist = Math.sqrt(Math.pow(longitude - pin.longitude,2)+Math.pow(latitude - pin.latitude,2));
            if (dist < radiusErr) {
                return true;
            }
        }
        return false;
    }

    // gets closest pin to current location if within radius error
    @Override
    public Pin getClosePin(double longitude, double latitude) {
        double radiusErr = 0.0001;
        for (Pin pin : locations()){
            double dist = Math.sqrt(Math.pow(longitude - pin.longitude,2)+Math.pow(latitude - pin.latitude,2));
            if (dist < radiusErr) {
                return pin;
            }
        }
        Pin pin = new Pin(0,0,'A');
        return pin;
    }
}