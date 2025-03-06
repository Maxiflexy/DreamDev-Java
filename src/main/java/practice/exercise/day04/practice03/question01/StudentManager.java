package practice.exercise.day04.practice03.question01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        return students.stream()
                .filter(student -> student.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(Student::displayStudentDetails);
        }
    }

    // **Find students with grade "A" using Streams**
    public List<Student> getStudentsWithGradeA() {
        return students.stream()
                .filter(student -> "A".equalsIgnoreCase(student.getGrade()))
                .collect(Collectors.toList());
    }

    // **Sort students by name (ascending order) using Streams**
    public List<Student> getStudentsSortedByName() {
        return students.stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }

    // **Filter students older than 18 using Streams**
    public List<Student> getStudentsOlderThan18() {
        return students.stream()
                .filter(student -> student.getAge() > 18)
                .collect(Collectors.toList());
    }

    // **Display all student names in uppercase using forEach()**
    public void displayStudentNamesInUppercase() {
        students.stream()
                .map(student -> student.getName().toUpperCase())
                .forEach(System.out::println);
    }

    // **Bonus: Using Functional Interfaces**
    public void applyPredicate(Predicate<Student> predicate) {
        students.stream()
                .filter(predicate)
                .forEach(Student::displayStudentDetails);
    }

    public void applyFunction(Function<Student, String> function) {
        students.stream()
                .map(function)
                .forEach(System.out::println);
    }

    public void applyConsumer(Consumer<Student> consumer) {
        students.forEach(consumer);
    }
}
