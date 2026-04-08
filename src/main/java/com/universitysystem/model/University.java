package com.universitysystem.model;

import java.util.List;

public class University {

    private List<Teacher> teachers;
    private List<Student> students;
    private List<CourseClass> classes;

    public University(List<Teacher> teachers, List<Student> students, List<CourseClass> classes) {
        this.classes = classes;
        this.students = students;
        this.teachers = teachers;
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