package practice.exercise.day04.practice03.question03;

import practice.exercise.day04.practice03.question01.Student;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class StudentServer {
    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager(); // Auto-load students

        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                new Thread(() -> handleClient(clientSocket, studentManager)).start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleClient(Socket clientSocket, StudentManager studentManager) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            out.println("Welcome to Student Search! Type a name to search or 'exit' to quit.");

            String name;
            while ((name = in.readLine()) != null) {
                if ("exit".equalsIgnoreCase(name)) {
                    out.println("Goodbye!");
                    break;
                }

                Student student = studentManager.searchStudentByName(name);
                if (student != null) {
                    out.println("Student Found: " + student);
                } else {
                    out.println("Student not found.");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

