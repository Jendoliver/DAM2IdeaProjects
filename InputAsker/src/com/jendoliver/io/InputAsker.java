package com.jendoliver.io;
/**
*
*  @author = Jendoliver ;)
*       TY Mar <3
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class InputAsker
{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private InputAsker() {}

    public static int askInt(String msg)
    {
        int num = 0;
        boolean error = true;
        do {
            System.out.println(msg);
            try {
                num = Integer.parseInt(br.readLine());
                error = false;
            } catch (IOException e) {
                System.out.println("Input error: " + e.getMessage());
            } catch (NumberFormatException ex) {
                System.out.println("Please, io an integer (example: 5)");
            }
        } while (error);
        return num;
    }

    public static int[] askIntArray(String msg, int arraySize)
    {
        int[] intArray = new int[arraySize];
        System.out.println(msg);
        for(int i = 0; i < arraySize; i++) {
            intArray[i] = askInt((i+1) + ": ");
        }
        return intArray;
    }

    public static ArrayList<Integer> askIntArrayList(String msg, int listSize)
    {
        ArrayList<Integer> integerList = new ArrayList<>();
        System.out.println(msg);
        for(int i = 0; i < listSize; i++) {
            integerList.add(askInt((i + 1) + ": "));
        }
        return integerList;
    }

    public static int askUnsignedInt(String msg)
    {
        int num = 0;
        boolean error = true;
        do {
            System.out.println(msg);
            try {
                num = Integer.parseInt(br.readLine());
                if (num < 0) {
                    System.out.println("Please provide an unsigned integer ( > 0 )");
                } else {
                    error = false;
                }
            } catch (IOException e) {
                System.out.println("Input error: " + e.getMessage());
            } catch (NumberFormatException ex) {
                System.out.println("Please, io an unsigned integer (example: 5)");
            }
        } while (error);
        return num;
    }

    public static int[] askUnsignedIntArray(String msg, int arraySize)
    {
        int[] unsignedIntArray = new int[arraySize];
        System.out.println(msg);
        for(int i = 0; i < arraySize; i++) {
            unsignedIntArray[i] = askUnsignedInt((i+1) + ": ");
        }
        return unsignedIntArray;
    }

    public static ArrayList<Integer> askUnsignedIntArrayList(String msg, int listSize)
    {
        ArrayList<Integer> unsignedIntegerList = new ArrayList<>();
        System.out.println(msg);
        for(int i = 0; i < listSize; i++) {
            unsignedIntegerList.add(askUnsignedInt((i + 1) + ": "));
        }
        return unsignedIntegerList;
    }

    public static double askDouble(String msg)
    {
        double num = 0;
        boolean error = true;
        do {
            System.out.println(msg);
            try {
                num = Double.parseDouble(br.readLine());
                error = false;
            } catch (IOException e) {
                System.out.println("Input error: " + e.getMessage());
            } catch (NumberFormatException ex) {
                System.out.println("Please, io a double (example: 5.2)");
            }
        } while (error);
        return num;
    }

    public static double[] askDoubleArray(String msg, int arraySize)
    {
        double[] doubleArray = new double[arraySize];
        System.out.println(msg);
        for(int i = 0; i < arraySize; i++) {
            doubleArray[i] = askDouble((i+1) + ": ");
        }
        return doubleArray;
    }

    public static ArrayList<Double> askDoubleArrayList(String msg, int listSize)
    {
        ArrayList<Double> doubleList = new ArrayList<>();
        System.out.println(msg);
        for(int i = 0; i < listSize; i++) {
            doubleList.add(askDouble(i + ": "));
        }
        return doubleList;
    }

    public static double askUnsignedDouble(String msg)
    {
        double num = 0;
        boolean error = true;
        do {
            System.out.println(msg);
            try {
                num = Double.parseDouble(br.readLine());
                if (num < 0) {
                    System.out.println("Please provide an unsigned double ( > 0 )");
                } else {
                    error = false;
                }
            } catch (IOException e) {
                System.out.println("Input error: " + e.getMessage());
            } catch (NumberFormatException ex) {
                System.out.println("Please, io an unsigned double (example: 5.2)");
            }
        } while (error);
        return num;
    }

    public static double[] askUnsignedDoubleArray(String msg, int arraySize)
    {
        double[] unsignedDoubleArray = new double[arraySize];
        System.out.println(msg);
        for(int i = 0; i < arraySize; i++) {
            unsignedDoubleArray[i] = askUnsignedDouble((i+1) + ": ");
        }
        return unsignedDoubleArray;
    }

    public static ArrayList<Double> askUnsignedDoubleArrayList(String msg, int listSize)
    {
        ArrayList<Double> unsignedDoubleList = new ArrayList<>();
        System.out.println(msg);
        for(int i = 0; i < listSize; i++) {
            unsignedDoubleList.add(askUnsignedDouble(i + ": "));
        }
        return unsignedDoubleList;
    }

    public static String askString(String msg)
    {
        String str = "";
        boolean error = true;
        do {
            System.out.println(msg);
            try {
                str = br.readLine();
                error = false;
            } catch (IOException e) {
                System.out.println("Input error: " + e.getMessage());
            }
        } while (error);
        return str;
    }

    public static ArrayList<String> askStringArrayList(String msg, int listSize)
    {
        ArrayList<String> stringList = new ArrayList<>();
        System.out.println(msg);
        for(int i = 0; i < listSize; i++) {
            stringList.add(askString((i+1) + ": "));
        }
        return stringList;
    }

    public static String askNonEmptyString(String msg)
    {
        String str = "";
        boolean error = true;
        do {
            System.out.println(msg);
            try {
                str = br.readLine();
                if(str.equals(""))  {
                    System.out.println("Please don't provide an empty string");
                } else {
                    error = false;
                }
            } catch (IOException e) {
                System.out.println("Input error: " + e.getMessage());
            }
        } while (error);
        return str;
    }

    public static ArrayList<String> askNonEmptyStringArrayList(String msg, int listSize)
    {
        ArrayList<String> nonEmptyStringList = new ArrayList<>();
        System.out.println(msg);
        for(int i = 0; i < listSize; i++) {
            nonEmptyStringList.add(askNonEmptyString((i+1) + ": "));
        }
        return nonEmptyStringList;
    }
}
