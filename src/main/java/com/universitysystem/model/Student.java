package com.universitysystem.model;

public class Student extends Person {

    private int studentId;
    private int studentCode;
    private int age;

    public Student(String name, int studentCode, int age) {
        super(name);
        this.studentCode = studentCode;
        this.age = age;
    }

    public Student(int studentId, String name, int studentCode, int age) {
        super(name);
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.age = age;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentCode() {
        return studentCode;
    }

    public int getAge() {
        return age;
    }
}