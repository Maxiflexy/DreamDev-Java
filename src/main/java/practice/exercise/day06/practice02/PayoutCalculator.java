package practice.exercise.day06.practice02;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PayoutCalculator {

    private Scanner scanner;
    private FileProcessor fileProcessor;

    public PayoutCalculator() {
        scanner = new Scanner(System.in);
        fileProcessor = new FileProcessor();
    }

    public void run() {
        System.out.println("Employee Payout Calculator");
        System.out.println("-------------------------");

        try {
            String filePath = getFilePathFromUser();
            List<EmployeeData> employees = fileProcessor.readEmployeesFromFile(filePath);

            if (employees.isEmpty()) {
                System.out.println("No employee data found in the file.");
                return;
            }

            displayPayoutResults(employees);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (InvalidDataFormatException e) {
            System.err.println("Invalid data format: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * Get file path from user input
     * @return the file path entered by the user
     */
    private String getFilePathFromUser() {
        System.out.print("Enter the absolute path of the employee data file: ");
        return scanner.nextLine();
    }

    /**
     * Display the payout results for all employees
     * @param employees list of employees
     */
    private void displayPayoutResults(List<EmployeeData> employees) {
        System.out.println("\nEmployee Payout Details:");
        System.out.println("------------------------");

        double totalPayout = 0;
        List<Double> individualPayouts = new ArrayList<>();

        for (EmployeeData employee : employees) {
            double payout = employee.calculatePayout();
            individualPayouts.add(payout);
            totalPayout += payout;

            System.out.printf("%s %s: $%.2f (%.2f × %d hours)%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    payout,
                    employee.getWagesPerHour(),
                    employee.getHoursWorked());
        }

        System.out.println("\nIndividual Payouts: " + individualPayouts);
        System.out.printf("\nTotal Payout: $%.2f%n", totalPayout);
    }

}
