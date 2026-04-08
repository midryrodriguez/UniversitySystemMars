package com.universitysystem.model;

import java.util.List;

public class University {

    private int universityId;
    private String name;
    private List<Teacher> teachers;
    private List<Student> students;
    private List<CourseClass> classes;

    public University(String name, List<Teacher> teachers, List<Student> students, List<CourseClass> classes) {
        this.name = name;
        this.teachers = teachers;
        this.students = students;
        this.classes = classes;
    }

    public University(int universityId, String name, List<Teacher> teachers, List<Student> students, List<CourseClass> classes) {
        this.universityId = universityId;
        this.name = name;
        this.teachers = teachers;
        this.students = students;
        this.classes = classes;
    }

    public int getUniversityId() {
        return universityId;
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<CourseClass> getClasses() {
        return classes;
    }
}