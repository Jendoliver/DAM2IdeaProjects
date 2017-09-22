package com.apporelbotna;

import java.util.ArrayList;

public class Subject
{
    public static int numberOfSubjects = 0;
    private String name;
    private String code;
    private ArrayList<Student> studentsIn;

    public Subject(String name, String code)
    {
        this.name = name;
        this.code = code;
        this.studentsIn = new ArrayList<>();
        numberOfSubjects++;
    }

    public String getName()             { return name; }
    public void setName(String name)    { this.name = name; }
    public String getCode()             { return code; }
    public void setCode(String code)    { this.code = code; }

    public ArrayList<Student> listStudents() {
        return studentsIn;
    }
    public void addStudent(Student student) {
        studentsIn.add(student);
    }

}
