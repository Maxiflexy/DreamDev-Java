package practice.exercise.day04.practice03.question03;

import practice.exercise.day04.practice03.question01.Student;

import java.util.ArrayList;
import java.util.List;

class StudentManager {
    private final List<Student> students;

    // Constructor initializes the list with 4 students
    public StudentManager() {
        this.students = new ArrayList<>();
        initializeStudents();
    }

    // Preload 4 students into the list
    private void initializeStudents() {
        students.add(new Student(1, "Maxi Dev", 29, "A"));
        students.add(new Student(2, "Onyekachi Kolbe", 18, "B"));
        students.add(new Student(3, "Viva Joy", 32, "A"));
        students.add(new Student(4, "Diana Techno", 22, "C"));
    }

    // Search for a student by name
    public Student searchStudentByName(String name) {
        return students.stream()
                .filter(student -> student.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    // Display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(Student::displayStudentDetails);
        }
    }
}

