package practice.exercise.day04.practice03.question02;

import practice.exercise.day04.practice03.question01.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private List<Student> students;

    public StudentManager() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully.");
    }

    public void removeStudentById(Integer id) {
        students.removeIf(student -> student.getId().equals(id));
        System.out.println("Student removed successfully.");
    }

    public Student searchStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                student.displayStudentDetails();
            }
        }
    }
}
