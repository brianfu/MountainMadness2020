package com.sfucsss.mountainmadness2020.bathroom;

import java.util.ArrayList;
import java.io.*;
import java.lang.String;

public class MyMap {

    int s1 = 0;
    int s2 = 11;
    int s3 = 23;

    public ArrayList<Pin> locations() {
        File file = new File("/pins.txt");
        FileReader fr;
        try {
            fr = new FileReader(file);
        } catch(FileNotFoundException f) {
            return null;
        }
        BufferedReader br = new BufferedReader(fr);
        ArrayList<Pin> pins = new ArrayList<Pin>();
        String st;
        do {
            try {
                st = br.readLine();
            } catch(IOException s) {
                return null;
            }
            double longitude = Double.parseDouble(st.substring(s1, st.indexOf(st, s1)));
            double latitude = Double.parseDouble(st.substring(s2, st.indexOf(st, s2)));
            char letter = st.charAt(s3);
            Pin p = new Pin(longitude, latitude, letter);
            pins.add(p);
        } while (st != null);
        return pins;
    }
}