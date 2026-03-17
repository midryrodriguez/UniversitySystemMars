package com.universitysystem.model;

public class Student extends Person {

    private int id ;
    private int age;

    public Student(String name, int id, int age) {
        super (name);
        this.id = id;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public int getAge () {
        return age;
    }
}
