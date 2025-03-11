package practice.exercise.day06.practice03;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class EmployeeProcessor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the absolute file path of the text file:");
        String inputFilePath = scanner.nextLine();

        String outputFilePath = "src/main/resources/employees_serialized_data" + ".maxidev";

        // Ensure the output directory exists
        File outputDir = new File("src/main/resources");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        FileProcessor fileProcessor = new FileProcessor();

        try {
            // Read employee data from text file
            List<Employee> employees = fileProcessor.readEmployeeData(inputFilePath);

            // Display read data
            System.out.println("\nEmployees read from file:");
            employees.forEach(System.out::println);

            // Serialize employee data to JSON
            fileProcessor.serializeEmployeesToJson(employees, outputFilePath);

            // Optional: Verify serialization by deserializing
            System.out.println("\nWould you like to verify the serialization? (y/n)");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("y")) {
                List<Employee> deserializedEmployees = fileProcessor.deserializeEmployeesFromJson(outputFilePath);

                System.out.println("\nDeserialized employees (JSON format):");
                fileProcessor.printEmployeesAsJson(deserializedEmployees);
            }

        } catch (IOException e) {
            System.err.println("File operation failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
