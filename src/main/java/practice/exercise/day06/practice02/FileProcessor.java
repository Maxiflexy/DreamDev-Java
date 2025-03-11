package practice.exercise.day06.practice02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    /**
     * Read employee data from a file
     * @param filePath path to the employee data file
     * @return list of Employee objects
     * @throws IOException if file cannot be read
     * @throws InvalidDataFormatException if data format is invalid
     */
    public List<EmployeeData> readEmployeesFromFile(String filePath) throws IOException, InvalidDataFormatException {
        List<EmployeeData> employees = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File does not exist: " + filePath);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }

                try {
                    EmployeeData employee = parseEmployeeData(line);
                    employees.add(employee);
                } catch (Exception e) {
                    throw new InvalidDataFormatException("Error parsing line " + lineNumber + ": " + line, e);
                }
            }
        }

        return employees;
    }

    /**
     * Parse a line of employee data in the format "FirstName|LastName|WagesPerHour|HoursWorked"
     * @param data the line of data to parse
     * @return an Employee object
     * @throws InvalidDataFormatException if the format is invalid
     */
    private EmployeeData parseEmployeeData(String data) throws InvalidDataFormatException {
        String[] parts = data.split("\\|");

        if (parts.length != 4) {
            throw new InvalidDataFormatException("Invalid data format. Expected FirstName|LastName|WagesPerHour|HoursWorked but got: " + data);
        }

        try {
            String firstName = parts[0].trim();
            String lastName = parts[1].trim();
            double wagesPerHour = Double.parseDouble(parts[2].trim());
            int hoursWorked = Integer.parseInt(parts[3].trim());

            return new EmployeeData(firstName, lastName, wagesPerHour, hoursWorked);
        } catch (NumberFormatException e) {
            throw new InvalidDataFormatException("Invalid number format in data: " + data, e);
        }
    }
}
