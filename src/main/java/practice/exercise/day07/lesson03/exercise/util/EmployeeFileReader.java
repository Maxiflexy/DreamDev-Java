package practice.exercise.day07.lesson03.exercise.util;

import practice.exercise.day07.lesson03.exercise.model.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFileReader {

    /**
     * Reads employee data from a file in the resources folder
     * @param filename The name of the file to read
     * @return List of Employee objects
     * @throws IOException If the file cannot be read
     */
    public static List<Employee> readEmployeesFromFile(String filename) throws IOException {
        List<Employee> employees = new ArrayList<>();

        // Use ClassLoader to get the resource as a stream
        InputStream inputStream = EmployeeFileReader.class.getClassLoader().getResourceAsStream(filename);

        if (inputStream == null) {
            throw new IOException("File not found: " + filename);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                Employee employee = parseEmployeeFromLine(line);
                if (employee != null) {
                    employees.add(employee);
                }
            }
        }

        return employees;
    }

    /**
     * Parses a line of text into an Employee object
     * Format: firstName|lastName|wagesPerHour|hoursWorked
     * @param line The line to parse
     * @return Employee object or null if parsing fails
     */
    private static Employee parseEmployeeFromLine(String line) {
        try {
            String[] parts = line.split("\\|");

            if (parts.length != 4) {
                System.err.println("Invalid line format: " + line);
                return null;
            }

            String firstName = parts[0].trim();
            String lastName = parts[1].trim();
            BigDecimal wagesPerHour = new BigDecimal(parts[2].trim());
            BigDecimal hoursWorked = new BigDecimal(parts[3].trim());

            return new Employee(firstName, lastName, wagesPerHour, hoursWorked);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number in line: " + line);
            return null;
        }
    }
}
