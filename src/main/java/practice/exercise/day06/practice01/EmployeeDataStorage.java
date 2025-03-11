package practice.exercise.day06.practice01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EmployeeDataStorage {

    private static final String FILE_PATH = "src/main/resources/employee_data.txt";

    public boolean saveEmployee(Employee employee) {
        try {
            File file = new File(FILE_PATH);

            // Create parent directories if they don't exist
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            // Check if file exists, create if it doesn't
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Created new employee data file.");
            }

            // Append to existing file
            try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                // Check if the file is empty (no need for comma at the beginning)
                if (file.length() > 0) {
                    writer.print("");
                }
                writer.print(employee.getFormattedData());
            }

            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }
}
