package com.universitysystem.service;

import com.universitysystem.model.CourseClass;
import com.universitysystem.model.FullTimeTeacher;
import com.universitysystem.model.PartTimeTeacher;
import com.universitysystem.model.Student;
import com.universitysystem.model.Teacher;
import com.universitysystem.model.University;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuService {

    private University university;
    private Scanner scanner;
    private DataPersistenceService dataPersistenceService;

    public MenuService(University university) {
        this.university = university;
        this.scanner = new Scanner(System.in);
        this.dataPersistenceService = new DataPersistenceService();
    }

    public void start() {
        String option;

        do {
            printMenu();
            option = scanner.nextLine().toLowerCase();

            switch (option) {
                case "a":
                    printAllTeachers();
                    break;
                case "b":
                    printAllClasses();
                    break;
                case "c":
                    createStudentAndAddToClass();
                    break;
                case "d":
                    createNewClass();
                    break;
                case "e":
                    findClassesByStudentCode();
                    break;
                case "f":
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }

            System.out.println();

        } while (!option.equals("f"));
    }

    private void printMenu() {
        System.out.println("===== UNIVERSITY MENU =====");
        System.out.println("a. Print all professors");
        System.out.println(" ");
        System.out.println("b. Print all classes");
        System.out.println(" ");
        System.out.println("c. Create new student and add to a class");
        System.out.println(" ");
        System.out.println("d. Create new class");
        System.out.println(" ");
        System.out.println("e. Find classes by student code");
        System.out.println(" ");
        System.out.println("f. Exit");
        System.out.println(" ");
        System.out.print("Choose an option: ");
    }

    private void printAllTeachers() {
        System.out.println("===== TEACHERS =====");

        for (Teacher teacher : university.getTeachers()) {
            String type = "";

            if (teacher instanceof FullTimeTeacher) {
                type = "Full Time Teacher";
            } else if (teacher instanceof PartTimeTeacher) {
                type = "Part Time Teacher";
            }

            System.out.println("Name: " + teacher.getName());
            System.out.println("Type: " + type);
            System.out.println("Base Salary: " + teacher.getBaseSalary());
            System.out.println("Calculated Salary: " + teacher.calculateSalary());
            System.out.println("-------------------------");
        }
    }

    private void printAllClasses() {
        System.out.println("===== CLASSES =====");

        int index = 1;

        for (CourseClass courseClass : university.getClasses()) {
            System.out.println(index + ". " + courseClass.getName());
            index++;
        }

        System.out.print("Select a class number to see details: ");

        int selectedIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (selectedIndex >= 0 && selectedIndex < university.getClasses().size()) {

            CourseClass selectedClass = university.getClasses().get(selectedIndex);

            System.out.println("Class: " + selectedClass.getName());
            System.out.println("Classroom: " + selectedClass.getClassroom());
            System.out.println("Teacher: " + selectedClass.getTeacher().getName());

            System.out.println("Students:");

            for (Student student : selectedClass.getStudents()) {
                System.out.println("- " + student.getName() + " (Code: " + student.getStudentCode() + ")");
            }

        } else {
            System.out.println("Invalid class selection.");
        }
    }

    private void createStudentAndAddToClass() {
        System.out.println("===== CREATE NEW STUDENT =====");

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student code: ");
        int studentCode = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter student age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("Select a class to add the student:");

        for (int i = 0; i < university.getClasses().size(); i++) {
            System.out.println((i + 1) + ". " + university.getClasses().get(i).getName());
        }

        System.out.print("Choose a class number: ");
        int classIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (classIndex >= 0 && classIndex < university.getClasses().size()) {

            Student newStudent = new Student(name, studentCode, age);

            university.getStudents().add(newStudent);

            CourseClass selectedClass = university.getClasses().get(classIndex);
            selectedClass.getStudents().add(newStudent);

            dataPersistenceService.saveUniversity(university);

            System.out.println("Student created and added successfully to " + selectedClass.getName());
        } else {
            System.out.println("Invalid class selection.");
        }
    }

    private void findClassesByStudentCode() {
        System.out.print("Enter student code: ");
        int studentCode = Integer.parseInt(scanner.nextLine());

        boolean found = false;

        for (CourseClass courseClass : university.getClasses()) {
            for (Student student : courseClass.getStudents()) {
                if (student.getStudentCode() == studentCode) {
                    if (!found) {
                        System.out.println("Student is enrolled in:");
                    }

                    System.out.println("- " + courseClass.getName());
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("No classes found for this student.");
        }
    }

    private void createNewClass() {
        System.out.println("===== CREATE NEW CLASS =====");

        System.out.print("Enter class name: ");
        String className = scanner.nextLine();

        System.out.print("Enter classroom: ");
        String classroom = scanner.nextLine();

        System.out.println("Select a teacher:");

        for (int i = 0; i < university.getTeachers().size(); i++) {
            Teacher teacher = university.getTeachers().get(i);
            System.out.println((i + 1) + ". " + teacher.getName());
        }

        System.out.print("Choose a teacher number: ");
        int teacherIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (teacherIndex < 0 || teacherIndex >= university.getTeachers().size()) {
            System.out.println("Invalid teacher selection.");
            return;
        }

        Teacher selectedTeacher = university.getTeachers().get(teacherIndex);

        System.out.println("Available students:");
        for (Student student : university.getStudents()) {
            System.out.println("Code: " + student.getStudentCode() + " - " + student.getName());
        }

        System.out.println("Enter student codes separated by commas:");
        String input = scanner.nextLine();

        String[] studentCodes = input.split(",");
        List<Student> selectedStudents = new ArrayList<>();

        for (String codeText : studentCodes) {
            int code = Integer.parseInt(codeText.trim());

            for (Student student : university.getStudents()) {
                if (student.getStudentCode() == code) {
                    selectedStudents.add(student);
                    break;
                }
            }
        }

        CourseClass newClass = new CourseClass(className, classroom, selectedTeacher, selectedStudents);
        university.getClasses().add(newClass);

        dataPersistenceService.saveUniversity(university);

        System.out.println("New class created successfully.");
    }
}