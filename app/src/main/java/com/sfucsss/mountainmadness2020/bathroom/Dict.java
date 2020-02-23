package com.sfucsss.mountainmadness2020.bathroom;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

public class Dict implements Dict_I{
    private HashSet<String> dictionary;

    public Dict() {
        dictionary = new HashSet<String>();
        File file = new File("dictionary.txt");
        Scanner input = new Scanner("dictionary.txt");
        while (input.hasNextLine()) {
            dictionary.add(input.nextLine());
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
