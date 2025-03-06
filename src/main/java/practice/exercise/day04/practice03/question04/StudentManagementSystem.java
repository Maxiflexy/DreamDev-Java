package practice.exercise.day04.practice03.question04;

import practice.exercise.day04.practice03.question01.Student;

import java.util.Scanner;

public class StudentManagementSystem {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManagerFile studentManager = new StudentManagerFile();


    public static void main(String[] args) {

        while (true) {
            System.out.println("\n\n1. Add Student");
            System.out.println("2. Remove Student by ID");
            System.out.println("3. Search Student by Name");
            System.out.println("4. Display All Students");
            System.out.println("5. Update Student Grade");
            System.out.println("6. Exit");
            System.out.print("\nChoose an option: ");

            int choice = getValidIntegerInput();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    studentManager.displayAllStudents();
                    break;
                case 5:
                    updateStudentGrade();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("   Enter Student ID: ");
        int id = getValidIntegerInput();

        System.out.print("   Enter Name: ");
        String name = getValidStringInput("Name");

        System.out.print("   Enter Age: ");
        int age = getValidIntegerInput();

        System.out.print("   Enter Grade: ");
        String grade = getValidStringInput("Grade");

        Student student = new Student(id, name, age, grade);
        studentManager.addStudent(student);
    }

    private static void removeStudent() {
        System.out.print("Enter Student ID to Remove: ");
        int id = getValidIntegerInput();
        studentManager.removeStudentById(id);
    }

    private static void searchStudent() {
        System.out.print("\nEnter Student Name to Search: ");
        String name = getValidStringInput("Name");
        Student foundStudent = studentManager.searchStudentByName(name);
        if (foundStudent != null) {
            foundStudent.displayStudentDetails();
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void updateStudentGrade() {
        System.out.print("\nEnter Student Name to Update Grade: ");
        String name = getValidStringInput("Name");
        Student student = studentManager.searchStudentByName(name);
        if (student != null) {
            System.out.print("Enter New Grade: ");
            String newGrade = getValidStringInput("Grade");
            student.updateGrade(newGrade);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static int getValidIntegerInput() {
        while (true) {
            String input = scanner.nextLine();
            if (input.matches("\\d+")) {
                return Integer.parseInt(input);
            }
            System.out.print("Invalid input. Please enter a valid number: ");
        }
    }

    private static String getValidStringInput(String fieldName) {
        while (true) {
            String input = scanner.nextLine();
            if (input.matches("[a-zA-Z ]+")) {
                return input;
            }
            System.out.print("Invalid input. Please enter a valid " + fieldName + " (letters only): ");
        }
    }
}
