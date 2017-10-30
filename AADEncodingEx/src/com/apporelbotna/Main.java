package com.apporelbotna;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    private static final String FILENAME = "encodedString.txt";

    public static void main(String[] args)
    {
        Encoder encoder = new Encoder();
        List<String> lines = new ArrayList<>();
        String strToCode = "que pasa subnormal";
        lines.add(encoder.encode(strToCode));
        Path fileToWrite = Paths.get(FILENAME);
        try {
            Files.write(fileToWrite, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(encoder.decode(FILENAME));
    }
}
