package com.apporelbotna;

/**
 * Created by the Focking Jendoliver on 06/10/2017
 * GitHub: github.com/Jendoliver
 */
public class NoCamerasException extends Exception
{
    public NoCamerasException() {
        super("There are no cameras registered. Please add a camera first.");
    }
}
