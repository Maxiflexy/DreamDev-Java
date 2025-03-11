package practice.exercise.day06.practice03;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class EmployeeProcessor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileProcessor fileProcessor = new FileProcessor();

        System.out.println("Choose an operation:");
        System.out.println("1. Process a text file and create a JSON file");
        System.out.println("2. Load and display an existing serialized JSON file");

        String choice = scanner.nextLine();

        try {
            if (choice.equals("1")) {
                processTextFile(scanner, fileProcessor);
            } else if (choice.equals("2")) {
                loadSerializedFile(scanner, fileProcessor);
            } else {
                System.out.println("Invalid choice. Exiting program.");
            }
        } catch (IOException e) {
            System.err.println("File operation failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void processTextFile(Scanner scanner, FileProcessor fileProcessor) throws IOException {
        System.out.println("Enter the absolute file path of the text file:");
        String inputFilePath = scanner.nextLine();


        String outputFilePath = "src/main/resources/employees_serialized2"+ ".maxidev";

        // Ensure the output directory exists
        File outputDir = new File("src/main/resources");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

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
    }

    private static void loadSerializedFile(Scanner scanner, FileProcessor fileProcessor) throws IOException {
        System.out.println("Enter the absolute file path of the serialized .maxidev file:");
        String filePath = scanner.nextLine();

        // Ensure the file has .maxidev extension
        if (!filePath.endsWith(".maxidev")) {
            System.out.println("Warning: The file does not have a .maxidev extension. Adding it automatically.");
            filePath += ".maxidev";
        }

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Error: The specified file does not exist: " + filePath);
            return;
        }

        // Deserialize the file
        List<Employee> employees = fileProcessor.deserializeEmployeesFromJson(filePath);

        // Display both object and JSON representations
        System.out.println("\nDeserialized employees from " + filePath + ":");
        System.out.println("\nObject representation:");
        employees.forEach(System.out::println);

        System.out.println("\nJSON representation:");
        fileProcessor.printEmployeesAsJson(employees);
    }
}
