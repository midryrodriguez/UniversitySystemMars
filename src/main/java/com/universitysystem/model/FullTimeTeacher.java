package com.universitysystem.model;

public class FullTimeTeacher extends Teacher{

    private int expYears;

    public FullTimeTeacher (String name, double baseSalary, int expYears) {
        super(name, baseSalary);
        this.expYears = expYears;
    }

    public int getExpYears() {
        return expYears;
    }

    @Override
    public double calculateSalary() {
        return baseSalary * (1.10 * expYears);
    }
}
