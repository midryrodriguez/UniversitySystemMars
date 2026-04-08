package com.universitysystem.model;

public abstract class Teacher extends Person {

    protected int teacherId;
    protected double baseSalary;

    public Teacher(String name, double baseSalary) {
        super(name);
        this.baseSalary = baseSalary;
    }

    public Teacher(int teacherId, String name, double baseSalary) {
        super(name);
        this.teacherId = teacherId;
        this.baseSalary = baseSalary;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public abstract double calculateSalary();
}