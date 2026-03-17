package com.universitysystem.service;

import com.universitysystem.model.CourseClass;
import com.universitysystem.model.FullTimeTeacher;
import com.universitysystem.model.PartTimeTeacher;
import com.universitysystem.model.Student;
import com.universitysystem.model.Teacher;
import com.universitysystem.model.University;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataSeeder {

    public static University createUniversity() {

        List<Teacher> teachers = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<CourseClass> classes = new ArrayList<>();

        // Full-time teachers
        Teacher teacher1 = new FullTimeTeacher("Carlos Gomez", 1200, 5);
        Teacher teacher2 = new FullTimeTeacher("Laura Martinez", 1500, 8);

        // Part-time teachers
        Teacher teacher3 = new PartTimeTeacher("Andres Lopez", 80, 20);
        Teacher teacher4 = new PartTimeTeacher("Sofia Ramirez", 90, 18);

        teachers.add(teacher1);
        teachers.add(teacher2);
        teachers.add(teacher3);
        teachers.add(teacher4);

        // Students
        Student student1 = new Student("Ana Torres", 101, 20);
        Student student2 = new Student("Juan Perez", 102, 21);
        Student student3 = new Student("Maria Castro", 103, 19);
        Student student4 = new Student("Luis Herrera", 104, 22);
        Student student5 = new Student("Camila Rojas", 105, 20);
        Student student6 = new Student("David Moreno", 106, 23);

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);

        // Classes
        CourseClass class1 = new CourseClass(
                "Mathematics",
                "Room 101",
                teacher1,
                 new ArrayList<>(Arrays.asList(student1, student2, student3))
                 );

        CourseClass class2 = new CourseClass(
                "Physics",
                "Room 102",
                teacher2,
                new ArrayList<>(Arrays.asList(student2, student4, student5))
        );

        CourseClass class3 = new CourseClass(
                "Programming",
                "Lab 201",
                teacher3,
                new ArrayList<>(Arrays.asList(student1, student5, student6))
        );

        CourseClass class4 = new CourseClass(
                "English",
                "Room 103",
                teacher4,
                new ArrayList<>(Arrays.asList(student3, student4, student6))
        );

        classes.add(class1);
        classes.add(class2);
        classes.add(class3);
        classes.add(class4);

        return new University(teachers, students, classes);
    }
}