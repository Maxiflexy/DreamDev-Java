package practice.exercise.day06.practice03;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FileProcessor {

    /**
     * Reads employee data from a text file
     * @param filePath the path to the input file
     * @return List of Employee objects
     */
    public List<Employee> readEmployeeData(String filePath) throws IOException {
        List<Employee> employees = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 4) {
                        String firstName = parts[0].trim();
                        String lastName = parts[1].trim();
                        double wagesPerHour = Double.parseDouble(parts[2].trim());
                        int hoursWorked = Integer.parseInt(parts[3].trim());

                        employees.add(new Employee(firstName, lastName, wagesPerHour, hoursWorked));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            throw e;
        }

        return employees;
    }

    /**
     * Serializes employee data to a JSON file with .maxidev extension
     * @param employees the list of employees to serialize
     * @param outputFilePath the path where the serialized file will be saved
     */
    public void serializeEmployeesToJson(List<Employee> employees, String outputFilePath) throws IOException {
        // Ensure the output file has .maxidev extension
        if (!outputFilePath.endsWith(".maxidev")) {
            outputFilePath += ".maxidev";
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilePath))) {
            writer.write("[\n");
            for (int i = 0; i < employees.size(); i++) {
                writer.write(employees.get(i).toJson());
                if (i < employees.size() - 1) {
                    writer.write(",\n");
                }
            }
            writer.write("\n]");
            System.out.println("JSON serialization completed successfully. File saved to: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error serializing employees to JSON: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Deserializes employee data from a JSON file
     * @param filePath the path to the serialized JSON file
     * @return List of Employee objects
     */
    public List<Employee> deserializeEmployeesFromJson(String filePath) throws IOException {
        List<Employee> employees = new ArrayList<>();

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Remove array brackets and split by employee objects
            content = content.trim();
            if (content.startsWith("[") && content.endsWith("]")) {
                content = content.substring(1, content.length() - 1);
            }

            // Split the content by looking for the pattern "},{"
            List<String> jsonObjects = new ArrayList<>();
            int startIndex = 0;

            // Handle the case of multiple JSON objects
            while (startIndex < content.length()) {
                int openBrace = content.indexOf("{", startIndex);
                if (openBrace == -1) break;

                int closeBrace = findMatchingCloseBrace(content, openBrace);
                if (closeBrace == -1) break;

                jsonObjects.add(content.substring(openBrace, closeBrace + 1));
                startIndex = closeBrace + 1;
            }

            // If we couldn't properly split, try a simpler approach for a single object
            if (jsonObjects.isEmpty() && content.contains("{") && content.contains("}")) {
                int start = content.indexOf("{");
                int end = content.lastIndexOf("}") + 1;
                jsonObjects.add(content.substring(start, end));
            }

            // Parse each JSON object
            for (String json : jsonObjects) {
                employees.add(Employee.fromJson(json));
            }

        } catch (IOException e) {
            System.err.println("Error deserializing employees from JSON: " + e.getMessage());
            throw e;
        }

        return employees;
    }

    /**
     * Find the matching closing brace for a JSON object
     */
    private int findMatchingCloseBrace(String content, int openBraceIndex) {
        int count = 0;
        for (int i = openBraceIndex; i < content.length(); i++) {
            if (content.charAt(i) == '{') {
                count++;
            } else if (content.charAt(i) == '}') {
                count--;
                if (count == 0) {
                    return i;
                }
            }
        }
        return -1; // No matching closing brace found
    }

    /**
     * Prints the employees as formatted JSON to the console
     * @param employees the list of employees to display as JSON
     */
    public void printEmployeesAsJson(List<Employee> employees) {
        for (Employee employee : employees) {
            System.out.println(employee.toJson());
            System.out.println(); // Add a blank line between employees
        }
    }
}
