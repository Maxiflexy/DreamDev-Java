package practice.exercise.day06.practice01;

import java.util.Scanner;

public class EmployeeManager {

    private final Scanner scanner;
    private final EmployeeDataStorage dataStorage;

    public EmployeeManager() {
        scanner = new Scanner(System.in);
        dataStorage = new EmployeeDataStorage();
    }

    public void run() {
        boolean running = true;

        System.out.println("-------------------------------");
        System.out.println("Employee Data Management System");
        System.out.println("-------------------------------");

        while (running) {
            System.out.println("\nOptions:");
            System.out.println("1. Add new employee payroll");
            System.out.println("2. Exit");
            System.out.print("Enter your choice (1-2): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addNewEmployee();
                    break;
                case "2":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.println("Closing application. Goodbye!");
        scanner.close();
    }

    private void addNewEmployee() {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        double wagesPerHour = 0;
        boolean validWages = false;
        while (!validWages) {
            System.out.print("Enter wages per hour: ");
            try {
                wagesPerHour = Double.parseDouble(scanner.nextLine());
                validWages = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for wages.");
            }
        }

        int hoursWorked = 0;
        boolean validHours = false;
        while (!validHours) {
            System.out.print("Enter hours worked: ");
            try {
                hoursWorked = Integer.parseInt(scanner.nextLine());
                validHours = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for hours worked.");
            }
        }

        Employee employee = new Employee(firstName, lastName, wagesPerHour, hoursWorked);
        boolean saved = dataStorage.saveEmployee(employee);

        if (saved) {
            System.out.println("Employee data saved successfully!");
        } else {
            System.out.println("Failed to save employee data.");
        }
    }
}
