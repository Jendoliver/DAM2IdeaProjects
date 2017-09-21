package com.apporelbotna;

public class Student
{
    private String name;
    private int age;

    public Student(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public void printName()
    {
        System.out.println(this); // Noice, si imprimes una instancia de un objeto se llama al metodo toString
    }

    @Override
    public String toString()
    {
        return "My name is " + this.name + " and I'm " + this.age + " years old.";
    }
}
