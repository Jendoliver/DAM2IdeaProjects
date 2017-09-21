package com.company;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce un numerito tete: ");
        int valor = sc.nextInt();
        long factorial = fact(valor);
        System.out.println(factorial);
        sc.close();
    }

    private static long fact(int n) // Recursive function
    {
        if(n == 1)
            return 1;
        return n * fact(n - 1);
    }

    // Hacer programa con una unica clase que liste los ficheros .exe y directorios/subdirectorios desde la raiz de la carpeta Java
}
