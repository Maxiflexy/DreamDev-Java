package practice.exercise.day08.lesson01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();
        boolean continueAdding = true;

        while (continueAdding) {

            Student student = getStudentDetails(scanner);
            students.add(student);

            // Ask if user wants to continue adding or display all
            System.out.print("\nEnter 'A' to add another student or 'D' to display all students: ");
            String choice = scanner.nextLine().toUpperCase();

            if (choice.equals("D")) {
                continueAdding = false;
                displayStudents(students);
            }
        }

        scanner.close();
    }

    private static Student getStudentDetails(Scanner scanner) {
        System.out.println("\nEnter student details:");

        // Get full name with validation
        String fullName = "";
        while (fullName.trim().isEmpty()) {
            System.out.print("Full Name: ");
            fullName = scanner.nextLine();
            if (fullName.trim().isEmpty()) {
                System.out.println("Name cannot be empty! Please enter a valid name.");
            }
        }

        // Get gender
        Gender gender = null;
        while (gender == null) {
            System.out.print("Gender (MALE/FEMALE): ");
            String genderInput = scanner.nextLine().toUpperCase();
            try {
                gender = Gender.valueOf(genderInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid gender! Please enter MALE or FEMALE.");
            }
        }

        // Get level
        Level level = null;
        while (level == null) {
            System.out.print("Level (UNDERGRADUATE/GRADUATE/PHD): ");
            String levelInput = scanner.nextLine().toUpperCase();
            try {
                level = Level.valueOf(levelInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid level! Please enter UNDERGRADUATE, GRADUATE, or PHD.");
            }
        }

        // Get matric number with validation
        String matricNo = "";
        while (matricNo.trim().isEmpty()) {
            System.out.print("Matric No: ");
            matricNo = scanner.nextLine();
            if (matricNo.trim().isEmpty()) {
                System.out.println("Matric No cannot be empty! Please enter a valid Matric No.");
            }
        }

        return new Student(fullName, gender, level, matricNo);
    }

    private static void displayStudents(List<Student> students) {
        System.out.println("\n=== List of Students ===");
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("%-25s %-10s %-15s %-10s\n", "Full Name", "Gender", "Level", "Matric No");
        System.out.println("-----------------------------------------------------------------");

        for (Student student : students) {
            System.out.printf("%-25s %-10s %-15s %-10s\n",
                    student.getFullName(),
                    student.getGender(),
                    student.getLevel(),
                    student.getMatricNo());
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("Total Students: " + students.size());
    }
}
