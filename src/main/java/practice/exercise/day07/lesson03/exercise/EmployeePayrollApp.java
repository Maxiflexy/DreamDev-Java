package practice.exercise.day07.lesson03.exercise;

import practice.exercise.day07.lesson03.exercise.model.Employee;
import practice.exercise.day07.lesson03.exercise.service.EmployeeDAO;
import practice.exercise.day07.lesson03.exercise.service.EmployeeDAOImpl;
import practice.exercise.day07.lesson03.exercise.util.DatabaseConnectionFactory;
import practice.exercise.day07.lesson03.exercise.util.EmployeeFileReader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollApp {

    private static final String EMPLOYEE_DATA_FILE = "employee_data.txt";
    private static List<Employee> employeesFromFile;
    private static EmployeeDAO employeeDAO;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        try (Connection connection = DatabaseConnectionFactory.getConnection()) {

            employeeDAO = new EmployeeDAOImpl(connection);

            boolean exit = false;
            while (!exit) {
                displayMenu();
                int choice = getUserChoice();

                switch (choice) {
                    case 1:
                        readTextFile();
                        break;
                    case 2:
                        addDataToDatabase();
                        break;
                    case 3:
                        readDataById();
                        break;
                    case 4:
                        readAllData();
                        break;
                    case 5:
                        deleteDataById();
                        break;
                    case 6:
                        exit = true;
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

                // Pause before showing menu again
                if (!exit) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
            }

        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n===== EMPLOYEE PAYROLL SYSTEM =====");
        System.out.println("1. Read text file");
        System.out.println("2. Add data to database");
        System.out.println("3. Read data by ID");
        System.out.println("4. Read all data");
        System.out.println("5. Delete data by ID");
        System.out.println("6. Exit");
        System.out.print("\nEnter your choice (1-6): ");
    }

    private static int getUserChoice() {
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            // Invalid input, will return 0 which will be handled as invalid choice
        }
        return choice;
    }

    private static void readTextFile() {
        System.out.println("\n--- READING EMPLOYEE DATA FROM FILE ---");
        try {
            employeesFromFile = EmployeeFileReader.readEmployeesFromFile(EMPLOYEE_DATA_FILE);
            System.out.println("Successfully read " + employeesFromFile.size() + " employee records from file.");

            // Display the first few records as preview
            int displayCount = Math.min(employeesFromFile.size(), 3);
            if (displayCount > 0) {
                System.out.println("\nPreview of data:");
                for (int i = 0; i < displayCount; i++) {
                    System.out.println((i+1) + ". " + employeesFromFile.get(i));
                }

                if (employeesFromFile.size() > displayCount) {
                    System.out.println("... and " + (employeesFromFile.size() - displayCount) + " more records.");
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static void addDataToDatabase() {
        System.out.println("\n--- ADDING EMPLOYEE DATA TO DATABASE ---");

        if (employeesFromFile == null || employeesFromFile.isEmpty()) {
            System.out.println("No employee data available. Please read the text file first (Option 1).");
            return;
        }

        try {
            System.out.print("Are you sure you want to add " + employeesFromFile.size() +
                    " employee records to the database? (y/n): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("y") || confirmation.equals("yes")) {
                int count = employeeDAO.saveAll(employeesFromFile);
                System.out.println("Successfully added " + count + " employee records to the database.");
            } else {
                System.out.println("Operation cancelled.");
            }

        } catch (SQLException e) {
            System.err.println("Error adding data to database: " + e.getMessage());
        }
    }

    private static void readDataById() {
        System.out.println("\n--- READING EMPLOYEE DATA BY ID ---");

        try {
            System.out.print("Enter employee ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            Employee employee = employeeDAO.findById(id);
            if (employee != null) {
                System.out.println("\nEmployee found:");
                System.out.println("ID: " + employee.getId());
                System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
                System.out.println("Wages per hour: $" + employee.getWagesPerHour());
                System.out.println("Hours worked: " + employee.getHoursWorked());
                System.out.println("Total salary: $" + employee.calculateTotalSalary());
            } else {
                System.out.println("No employee found with ID: " + id);
            }

        } catch (NumberFormatException e) {
            System.err.println("Invalid ID format. Please enter a number.");
        } catch (SQLException e) {
            System.err.println("Error reading from database: " + e.getMessage());
        }
    }

    private static void readAllData() {
        System.out.println("\n--- READING ALL EMPLOYEE DATA ---");

        try {
            List<Employee> employees = employeeDAO.findAll();

            if (employees.isEmpty()) {
                System.out.println("No employee records found in the database.");
            } else {
                System.out.println("Found " + employees.size() + " employee records:");
                System.out.println("\nID | Name | Wages/Hour | Hours | Total Salary");
                System.out.println("------------------------------------------------");

                for (Employee employee : employees) {
                    System.out.printf("%-3d | %-20s | $%-10.2f | %-6.2f | $%-10.2f%n",
                            employee.getId(),
                            employee.getFirstName() + " " + employee.getLastName(),
                            employee.getWagesPerHour(),
                            employee.getHoursWorked(),
                            employee.calculateTotalSalary());
                }
            }

        } catch (SQLException e) {
            System.err.println("Error reading from database: " + e.getMessage());
        }
    }

    private static void deleteDataById() {
        System.out.println("\n--- DELETING EMPLOYEE DATA BY ID ---");

        try {
            System.out.print("Enter employee ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            // First check if the employee exists
            Employee employee = employeeDAO.findById(id);
            if (employee == null) {
                System.out.println("No employee found with ID: " + id);
                return;
            }

            // Confirm deletion
            System.out.print("Are you sure you want to delete employee " +
                    employee.getFirstName() + " " + employee.getLastName() +
                    " (ID: " + id + ")? (y/n): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("y") || confirmation.equals("yes")) {
                boolean deleted = employeeDAO.deleteById(id);
                if (deleted) {
                    System.out.println("Employee deleted successfully.");
                } else {
                    System.out.println("Failed to delete employee.");
                }
            } else {
                System.out.println("Deletion cancelled.");
            }

        } catch (NumberFormatException e) {
            System.err.println("Invalid ID format. Please enter a number.");
        } catch (SQLException e) {
            System.err.println("Error deleting from database: " + e.getMessage());
        }
    }
}
