package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main
{
    private static ArrayList<Album> albums = new ArrayList();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        int option;
        do {
            printMenu();
            option = askOption();
            decode(option);
        } while (option != 5);
        sc.close();
    }

    // Main helper methods
    private static void printMenu()
    {
        System.out.println("--- MAIN MENU ---");
        System.out.println("1. Add album to collection");
        System.out.println("2. Modify album");
        System.out.println("3. Delete album");
        System.out.println("4. Print album");
        System.out.println("5. Exit");
    }
    private static int askOption() // Asks an option for the main menu
    {
        int option = 5;
        boolean flag = false;
        System.out.println("Select an option: ");
        do {
            try {
                option = sc.nextInt();
                flag = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer... And sorry for the infinite loop :(");
            }
        } while ( ! flag);
        return option;
    }
    private static void decode(int option) // Given an option, leads to a main method
    {
        switch(option)
        {
            case 1: addAlbum(); break;
            case 2: modifyAlbum(); break;
            case 3: deleteAlbum(); break;
            case 4: printAlbumDetails(); break;
            case 5: break;
        }
    }

    // Main methods of the application
    private static void addAlbum()
    {
        System.out.println("--- ADDING A NEW ALBUM ---");
        albums.add(askAlbumDetails());
        System.out.println("Album successfully added!");
    }
    private static void modifyAlbum()
    {
        System.out.println("Select the album you want to modify:");
        printAlbums();
        int albumToModifyIndex = sc.nextInt();
            if( albumToModifyIndex == 0 ) return;
        Album albumToModify = albums.get(albumToModifyIndex-1);
        System.out.println("--- Actual details of the album ---");
        System.out.println(albumToModify.details());

        System.out.println("--- Please, provide new details here ---");
        Album albumModified = askAlbumDetails();
        albumToModify.setTitle(albumModified.getTitle());
        albumToModify.setGroup(albumModified.getGroup());
        albumToModify.setNsongs(albumModified.getNsongs());
        albumToModify.setDuration(albumModified.getDuration());

        System.out.println("Album successfully modified!");
    }
    private static void deleteAlbum()
    {
        System.out.println("Select the album you want to delete:");
        printAlbums();
        int albumToDeleteIndex = sc.nextInt();
            if( albumToDeleteIndex == 0 ) return;
        albums.remove(albumToDeleteIndex-1);
        System.out.println("Album successfully deleted!");
    }
    private static void printAlbumDetails()
    {
        System.out.println("Select the album from which you desire to obtain more info:");
        printAlbums();
        int albumToGetInfoFromIndex = sc.nextInt();
            if( albumToGetInfoFromIndex == 0 ) return;
        System.out.println(albums.get(albumToGetInfoFromIndex-1).details());
    }

    // Helper secondary methods
    private static void printAlbums()
    {
        for(int i = 0; i < albums.size(); i++)
        {
            System.out.println( (i+1) + ": " + albums.get(i).getTitle());
        }
        System.out.println("0: Exit");
    }
    private static Album askAlbumDetails()
    {
        String title, group;
        int nsongs; float duration;
        System.out.println("Title of the album:");
        sc.nextLine();
        title = sc.nextLine();

        System.out.println("Name of the group:");
        group = sc.nextLine();

        System.out.println("Number of songs:");
        nsongs = sc.nextInt();

        System.out.println("Duration (IMPORTANT: input the float with a comma, not with a dot!!!):");
        duration = sc.nextFloat();

        return new Album(title, group, nsongs, duration);
    }
}
