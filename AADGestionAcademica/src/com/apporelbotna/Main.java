package com.apporelbotna;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        int option;
        do {
            printMenu();
            option = askOption();
            decode(option);
        } while (option != 10);
        sc.close();
    }

    // Helper methods
    private static void printMenu()
    {
        System.out.println("--- MAIN MENU ---");
        System.out.println("1. Add new student to the school");
        System.out.println("2. Add new subject to the school");
        System.out.println("3. Add an existing student to an existing subject");
        System.out.println("4. List all students");
        System.out.println("5. List all subjects");
        System.out.println("6. List students on a particular subject");
        System.out.println("7. Remove student from a subject (keeping the student");
        System.out.println("8. Delete student from the school");
        System.out.println("9. Delete subject from the school");
        System.out.println("10. Exit");
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
            case 1: Secretary.addNewStudent(); break;
            case 2: Secretary.addNewSubject(); break;
            case 3: Secretary.addStudentToSubject(); break;
            case 4: Secretary.listAllStudents(); break;
            case 5: Secretary.listAllSubjects(); break;
            case 6: Secretary.listStudentsOnSubject(); break;
            case 7: Secretary.removeStudentFromSubject(); break;
            case 8: Secretary.deleteStudent(); break;
            case 9: Secretary.deleteSubject(); break;
            case 10: break;
        }
    }

}