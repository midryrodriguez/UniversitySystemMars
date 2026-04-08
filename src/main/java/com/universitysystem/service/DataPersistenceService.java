package com.universitysystem.service;

import com.universitysystem.model.CourseClass;
import com.universitysystem.model.FullTimeTeacher;
import com.universitysystem.model.PartTimeTeacher;
import com.universitysystem.model.Student;
import com.universitysystem.model.Teacher;
import com.universitysystem.model.University;
import com.universitysystem.util.FileType;
import com.universitysystem.util.FileUtil;
import com.universitysystem.util.IFileUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataPersistenceService {

    private static final String DB_PATH = "C:\\Users\\midry.rodriguez\\IdeaProjects\\UniversitySystem\\DB";
    private static final String FILE_NAME = FileType.UNIVERSITY_DATA.getFileName();
    private static final String FULL_PATH = DB_PATH + "\\" + FILE_NAME;

    private final IFileUtil fileUtil = new FileUtil();

    public void saveUniversity(University university) {
        fileUtil.createFile(DB_PATH, FILE_NAME);
        fileUtil.clearFile(FULL_PATH);

        fileUtil.addLineToFile(FULL_PATH, "===== UNIVERSITY DATA =====");
        fileUtil.addLineToFile(FULL_PATH, "");

        fileUtil.addLineToFile(FULL_PATH, "===== TEACHERS =====");
        for (Teacher teacher : university.getTeachers()) {
            fileUtil.addLineToFile(FULL_PATH, "Name: " + teacher.getName());

            if (teacher instanceof FullTimeTeacher) {
                FullTimeTeacher fullTimeTeacher = (FullTimeTeacher) teacher;
                fileUtil.addLineToFile(FULL_PATH, "Type: Full Time Teacher");
                fileUtil.addLineToFile(FULL_PATH, "Base Salary: " + teacher.getBaseSalary());
                fileUtil.addLineToFile(FULL_PATH, "Experience Years: " + fullTimeTeacher.getExpYears());
            } else if (teacher instanceof PartTimeTeacher) {
                PartTimeTeacher partTimeTeacher = (PartTimeTeacher) teacher;
                fileUtil.addLineToFile(FULL_PATH, "Type: Part Time Teacher");
                fileUtil.addLineToFile(FULL_PATH, "Base Salary: " + teacher.getBaseSalary());
                fileUtil.addLineToFile(FULL_PATH, "Active Hours Per Week: " + partTimeTeacher.getActiveHoursPerWeek());
            }

            fileUtil.addLineToFile(FULL_PATH, "Calculated Salary: " + teacher.calculateSalary());
            fileUtil.addLineToFile(FULL_PATH, "-------------------------");
        }

        fileUtil.addLineToFile(FULL_PATH, "");
        fileUtil.addLineToFile(FULL_PATH, "===== STUDENTS =====");
        for (Student student : university.getStudents()) {
            fileUtil.addLineToFile(FULL_PATH, "Name: " + student.getName());
            fileUtil.addLineToFile(FULL_PATH, "ID: " + student.getId());
            fileUtil.addLineToFile(FULL_PATH, "Age: " + student.getAge());
            fileUtil.addLineToFile(FULL_PATH, "-------------------------");
        }

        fileUtil.addLineToFile(FULL_PATH, "");
        fileUtil.addLineToFile(FULL_PATH, "===== CLASSES =====");
        for (CourseClass courseClass : university.getClasses()) {
            fileUtil.addLineToFile(FULL_PATH, "Class Name: " + courseClass.getName());
            fileUtil.addLineToFile(FULL_PATH, "Classroom: " + courseClass.getClassroom());
            fileUtil.addLineToFile(FULL_PATH, "Teacher: " + courseClass.getTeacher().getName());
            fileUtil.addLineToFile(FULL_PATH, "Students:");

            for (Student student : courseClass.getStudents()) {
                fileUtil.addLineToFile(FULL_PATH, "- " + student.getName() + " (ID: " + student.getId() + ")");
            }

            fileUtil.addLineToFile(FULL_PATH, "-------------------------");
        }

        System.out.println("Datos guardados en: " + FULL_PATH);
    }

    public University loadUniversity() {
        if (!fileUtil.existsFile(FULL_PATH)) {
            return null;
        }

        List<String> lines = fileUtil.readAllLines(FULL_PATH);

        if (lines.isEmpty()) {
            return null;
        }

        List<Teacher> teachers = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<CourseClass> classes = new ArrayList<>();

        Map<String, Teacher> teacherMap = new HashMap<>();
        Map<Integer, Student> studentMap = new HashMap<>();

        String section = "";

        int i = 0;
        while (i < lines.size()) {
            String line = lines.get(i).trim();

            if (line.isEmpty()) {
                i++;
                continue;
            }

            if (line.equals("===== TEACHERS =====")) {
                section = "TEACHERS";
                i++;
                continue;
            }

            if (line.equals("===== STUDENTS =====")) {
                section = "STUDENTS";
                i++;
                continue;
            }

            if (line.equals("===== CLASSES =====")) {
                section = "CLASSES";
                i++;
                continue;
            }

            if (section.equals("TEACHERS") && line.startsWith("Name: ")) {
                String name = line.substring("Name: ".length()).trim();
                String type = lines.get(++i).trim().substring("Type: ".length()).trim();
                double baseSalary = Double.parseDouble(lines.get(++i).trim().substring("Base Salary: ".length()).trim());

                Teacher teacher;

                if (type.equals("Full Time Teacher")) {
                    int expYears = Integer.parseInt(lines.get(++i).trim().substring("Experience Years: ".length()).trim());
                    i++; // Calculated Salary
                    teacher = new FullTimeTeacher(name, baseSalary, expYears);
                } else {
                    int activeHoursPerWeek = Integer.parseInt(lines.get(++i).trim().substring("Active Hours Per Week: ".length()).trim());
                    i++; // Calculated Salary
                    teacher = new PartTimeTeacher(name, baseSalary, activeHoursPerWeek);
                }

                while (i < lines.size() && !lines.get(i).trim().equals("-------------------------")) {
                    i++;
                }

                teachers.add(teacher);
                teacherMap.put(name, teacher);
            } else if (section.equals("STUDENTS") && line.startsWith("Name: ")) {
                String name = line.substring("Name: ".length()).trim();
                int id = Integer.parseInt(lines.get(++i).trim().substring("ID: ".length()).trim());
                int age = Integer.parseInt(lines.get(++i).trim().substring("Age: ".length()).trim());

                Student student = new Student(name, id, age);
                students.add(student);
                studentMap.put(id, student);

                while (i < lines.size() && !lines.get(i).trim().equals("-------------------------")) {
                    i++;
                }
            } else if (section.equals("CLASSES") && line.startsWith("Class Name: ")) {
                String className = line.substring("Class Name: ".length()).trim();
                String classroom = lines.get(++i).trim().substring("Classroom: ".length()).trim();
                String teacherName = lines.get(++i).trim().substring("Teacher: ".length()).trim();

                Teacher teacher = teacherMap.get(teacherName);
                List<Student> classStudents = new ArrayList<>();

                i++; // Students:

                while (i + 1 < lines.size() && lines.get(i + 1).trim().startsWith("- ")) {
                    String studentLine = lines.get(++i).trim();

                    int startId = studentLine.lastIndexOf("(ID: ");
                    int endId = studentLine.lastIndexOf(")");

                    if (startId != -1 && endId != -1) {
                        String idText = studentLine.substring(startId + 5, endId).trim();
                        int studentId = Integer.parseInt(idText);

                        Student student = studentMap.get(studentId);
                        if (student != null) {
                            classStudents.add(student);
                        }
                    }
                }

                CourseClass courseClass = new CourseClass(className, classroom, teacher, classStudents);
                classes.add(courseClass);
            }

            i++;
        }

        return new University(teachers, students, classes);
    }
}