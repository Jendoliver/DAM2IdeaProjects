package com.apporelbotna;

import com.jendoliver.io.InputAsker;
import java.io.File;
import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        File dir;
        dir = (args.length > 0) ? new File(args[0]) : new File(InputAsker.askNonEmptyString("Input the route of the directory: "));
        if (!dir.exists()) {
            System.out.println("Directory not found");
            return;
        }
        System.out.println("Directory found!");
        File[] files = dir.listFiles();
        assert files != null;

        for(File file : files) {
            if(file.isFile()) {
                String newDirName = file.getAbsolutePath().replaceFirst("[.][^.]+$", "");
                File newDir = new File(newDirName);
                if(newDir.mkdirs()) {
                    System.out.printf("Directory %s successfully created!%n", newDirName);
                } else {
                    System.out.println("Failed to create directory");
                }
            } else if(file.isDirectory()) {
                try {
                    File newFile = new File(String.format("%s.txt", file.getAbsolutePath()));
                    if(newFile.createNewFile()) {
                        System.out.printf("File %s successfully created!%n", newFile.getName());
                    } else {
                        System.out.println("Failed to create directory");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
