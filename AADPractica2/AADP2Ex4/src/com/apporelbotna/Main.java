package com.apporelbotna;

import com.jendoliver.io.InputAsker;
import java.io.File;

public class Main {

    public static void main(String[] args)
    {
        File dir;
        dir = (args.length > 0) ? new File(args[0]) : new File(InputAsker.askNonEmptyString("Input the route of the file to search: "));
        if( ! dir.exists()) {
            System.out.println("Directory not found");
            return;
        }
        System.out.println("Directory found!");
        File[] files = dir.listFiles();
        assert files != null;

        System.out.println("--- LS ---");
        for (File file : files) {
            if(file.isFile()) {
                System.out.println(file.getName() + " - File");
            } else if (file.isDirectory()) {
                System.out.println(file.getName() + " - Directory");
            }
        }
    }
}
