package com.universitysystem.model;

import java.util.List;

public class CourseClass {

    private int classId;
    private String name;
    private String classroom;
    private Teacher teacher;
    private List<Student> students;

    public CourseClass(String name, String classroom, Teacher teacher, List<Student> students) {
        this.name = name;
        this.classroom = classroom;
        this.teacher = teacher;
        this.students = students;
    }

    public CourseClass(int classId, String name, String classroom, Teacher teacher, List<Student> students) {
        this.classId = classId;
        this.name = name;
        this.classroom = classroom;
        this.teacher = teacher;
        this.students = students;
    }

    public int getClassId() {
        return classId;
    }

    public String getName() {
        return name;
    }

    public String getClassroom() {
        return classroom;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public List<Student> getStudents() {
        return students;
    }
}