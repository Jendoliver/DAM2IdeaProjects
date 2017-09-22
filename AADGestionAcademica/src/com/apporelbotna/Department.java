package com.apporelbotna;

import java.util.ArrayList;

public class Department
{
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Subject> subjects = new ArrayList<>();

    private Department() {}

    public static ArrayList<Student> getStudents() {
        return students;
    }
    public static ArrayList<Subject> getSubjects() {
        return subjects;
    }
    public static void addStudent(Student student) {
        students.add(student);
    }
    public static void addSubject(Subject subject) {
        subjects.add(subject);
    }
}
