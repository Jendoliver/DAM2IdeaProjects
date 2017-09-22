package com.apporelbotna;

public class Student
{
    private String name;
    private String dni;

    public Student(String name, String dni)
    {
        this.name = name;
        this.dni = dni;
    }

    public String getName()             { return name; }
    public void setName(String name)    { this.name = name; }
    public String getDni()              { return dni; }
    public void setDni(String dni)      { this.dni = dni; }
}
