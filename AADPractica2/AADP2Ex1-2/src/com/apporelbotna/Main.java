package com.apporelbotna;

import com.jendoliver.io.InputAsker;

import java.io.File;

public class Main
{
    // Escriu un programa que donat un directori escrigui per pantalla
    // el nom del fitxer més gran que conté. Ha de comprovar si el directori és vàlid.
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

	    long maxSizeOfFile = Long.MIN_VALUE;
	    int biggerFileIndex = 0;
        int i = 0;
        for(File file : files) {
            if(file.isFile()) {
                if(file.length() > maxSizeOfFile) {
                    maxSizeOfFile = file.length();
                    biggerFileIndex = i;
                }
            }
            i++;
        }
        System.out.println("The biggest file on this directory is: " + files[biggerFileIndex].getName());
    }
}
