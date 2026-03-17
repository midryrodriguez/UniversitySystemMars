package com.universitysystem.model;

public class PartTimeTeacher extends  Teacher{


private int activeHoursPerWeek;

public  PartTimeTeacher (String name, double baseSalary, int  activeHoursPerWeek) {
    super(name, baseSalary);
    this.activeHoursPerWeek = activeHoursPerWeek;
}

    public int getActiveHoursPerWeek() {
        return activeHoursPerWeek;
    }

@Override
public double calculateSalary(){
    return baseSalary * activeHoursPerWeek;
}
}
