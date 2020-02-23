package com.sfucsss.mountainmadness2020.bathroom;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;

public class Dict implements Dict_I{
    private HashSet<String> dictionary;

    public Dict(Context context) {
        dictionary = new HashSet<String>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getResources().openRawResource(R.raw.dictionary)));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                dictionary.add(mLine);
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
    public Dict(HashSet<String> dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public boolean isValid(String word) {
        return dictionary.contains(word);
    }
}
