package practice.exercise.day04.practice02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {

    private static final String SERVER_ADDRESS = "localhost"; // Server address
    private static final int SERVER_PORT = 8080; // Server port

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to chat server.");
            System.out.println(input.readLine()); // Read welcome message from server

            String userMessage;
            while (true) {
                System.out.print("You: ");
                userMessage = consoleInput.readLine(); // Read user input

                output.println(userMessage); // Send message to server

                if ("exit".equalsIgnoreCase(userMessage)) {
                    System.out.println("Disconnected from chat server.");
                    break;
                }

                String serverResponse = input.readLine(); // Receive response from server
                System.out.println(serverResponse);
            }
        } catch (IOException e) {
            System.err.println("Error in client: " + e.getMessage());
        }
    }
}