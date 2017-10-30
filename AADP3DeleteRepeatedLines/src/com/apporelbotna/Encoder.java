package com.apporelbotna;

import java.util.Random;

public class Encoder
{
    private int seed;

    public Encoder() {
        seed = new Random();
    }

    public String encode(String toCode) {
        String output = "";
        // for each position of string
            // Integer.parseInt(char) + seed
            // put in output
        return output;
    }

    public String decode() {
        String output = "";
        // for each line of file
            // Integer.parseInt(char) - seed
            // (String) intChar
            // put in output
        return output;
    }
}
