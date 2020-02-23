package com.sfucsss.mountainmadness2020.bathroom;

import android.content.Context;

import java.util.ArrayList;
import java.io.*;
import java.lang.String;
import java.lang.Math.*;

public class MyMap implements MyMap_I{

    public ArrayList<Pin> pins;

    public MyMap(Context context) {
        BufferedReader reader = null;
        pins = new ArrayList<Pin>();
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getResources().openRawResource(R.raw.pins)));

            // do reading, usually loop until end of file reading
            for (String st = reader.readLine(); st != null; st = reader.readLine()) {
                String[] temp = st.split(" ");
                double longitude = Double.parseDouble(temp[0]);
                double latitude = Double.parseDouble(temp[1]);
                char letter = temp[2].charAt(0);
                Pin p = new Pin(longitude, latitude, letter);
                pins.add(p);
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
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