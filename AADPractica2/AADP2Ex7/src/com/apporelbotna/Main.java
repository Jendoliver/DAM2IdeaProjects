package com.apporelbotna;

import com.jendoliver.io.InputAsker;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class Main
{
    private static File[] files;
    public static void main(String[] args)
    {
        File dir;
        dir = (args.length > 0) ? new File(args[0]) : new File(InputAsker.askNonEmptyString("Input the route of the directory: "));
        if (!dir.exists()) {
            System.out.println("Directory not found");
            return;
        }
        System.out.println("Directory found!");
        files = dir.listFiles();
        assert files != null;

        int option;
        do {
            printMenu();
            option = InputAsker.askIntBetween("Select an option: ", 1, 4);
            decode(option);
        } while (option != 4);
    }

    private static void printMenu()
    {
        System.out.println("--- SELECT AN OPTION ---");
        System.out.println("1 - Sort alphabetical");
        System.out.println("2 - Sort by modification date");
        System.out.println("3 - Sort by file size");
        System.out.println("4 - Exit");
    }

    private static void decode(int option)
    {
        switch(option)
        {
            case 1: sortFilesAlphabetical(); break;
            case 2: sortFilesByModificationDate(); break;
            case 3: sortFilesBySize(); break;
        }
    }

    private static void sortFilesAlphabetical()
    {
        System.out.println("- FILES SORTED ALPHABETICALLY -");
        Arrays.sort(files);
        for(File file : files) {
            System.out.println(file.getName());
        }
    }

    private static void sortFilesByModificationDate()
    {
        System.out.println("- FILES SORTED BY MODIFICATION DATE -");
        Arrays.sort(files, Comparator.comparingLong(File::lastModified));
        for(File file : files) {
            System.out.println(file.getName());
        }
    }

    private static void sortFilesBySize()
    {
        System.out.println("- FILES SORTED BY SIZE -");
        Arrays.sort(files, Comparator.comparingLong(File::length));
        for(File file : files) {
            System.out.println(file.getName());
        }
    }
}
