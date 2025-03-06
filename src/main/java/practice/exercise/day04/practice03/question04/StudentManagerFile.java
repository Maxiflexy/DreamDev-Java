package practice.exercise.day04.practice03.question04;

import practice.exercise.day04.practice03.question01.Student;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class StudentManagerFile {
    private static final String FILE_PATH = "src/main/resources/students.txt";
    private List<Student> students;

    public StudentManagerFile() {
        this.students = new ArrayList<>();
        loadStudentsFromFile(); // Load students when the program starts
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToFile();
        System.out.println("Student added successfully.");
    }

    public void removeStudentById(Integer id) {
        if (students.removeIf(student -> student.getId().equals(id))) {
            saveStudentsToFile();
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
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

    // **Save students to file**
    public void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : students) {
                writer.write(student.getId() + "," + student.getName() + "," +
                        student.getAge() + "," + student.getGrade());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving students to file: " + e.getMessage());
        }
    }

    // **Load students from file**
    public void loadStudentsFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    Student student = new Student(
                            Integer.parseInt(data[0]),
                            data[1],
                            Integer.parseInt(data[2]),
                            data[3]
                    );
                    students.add(student);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading students from file: " + e.getMessage());
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

