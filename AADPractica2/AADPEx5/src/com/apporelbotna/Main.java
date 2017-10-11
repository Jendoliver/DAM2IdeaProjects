package com.apporelbotna;

import com.jendoliver.io.InputAsker;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) {
        File dir;
        dir = (args.length > 0) ? new File(args[0]) : new File(InputAsker.askNonEmptyString("Input the route of the directory whose files starting with numbers shall be deleted: "));
        if (!dir.exists()) {
            System.out.println("Directory not found");
            return;
        }
        System.out.println("Directory found!");
        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return Character.isDigit(name.charAt(0));
            }
        });
        assert files != null;

        for(File file : files) {
            if(file.isFile()) {
                try {
                    Files.delete(file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}