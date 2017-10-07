package com.apporelbotna;

import com.jendoliver.io.InputAsker;
import java.io.File;
import java.io.FilenameFilter;

public class Main
{
    public static void main(String[] args)
    {
        File dir;
        dir = (args.length > 0) ? new File(args[0]) : new File(InputAsker.askNonEmptyString("Input the route of the file to search: "));
        if( ! dir.exists()) {
            System.out.println("Directory not found");
            return;
        }
        System.out.println("Directory found!");
        String stringToSearch = InputAsker.askNonEmptyString("Input the word to search in the file names: ");
        File[] files = dir.listFiles((dir1, name) -> name.toLowerCase().contains(stringToSearch.toLowerCase()));
        assert files != null;
        for (File file : files) {
            if(file.isFile()) {
                System.out.println(file.getName());
            }
        }
    }
}
