package com.apporelbotna;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Encoder
{
    private int SEED;
    private long UTF8_MAX = 4294967296L;

    public Encoder() {
        SEED = new Random().nextInt(256);
    }

    public String encode(String toCode) {
        StringBuilder output = new StringBuilder();
        for(char c : toCode.toCharArray()) {
            long longChar = ((long)c + SEED) % UTF8_MAX; // modulo0o0o0
            output.append((char)longChar);
        }
        return output.toString();
    }

    public String decode(String fileName) {
        StringBuilder fileText = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null) {
                fileText.append(line);
                fileText.append(System.lineSeparator());
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // for each line of file
        StringBuilder output = new StringBuilder();
        for(char c : fileText.toString().toCharArray()) {
            long longChar =  Math.floorMod(((long)c - SEED), UTF8_MAX);
            output.append((char)longChar);
        }
        return output.toString();
    }
}
