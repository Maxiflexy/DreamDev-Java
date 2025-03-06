package practice.exercise.day04.practice02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// Separate thread to handle client communication
public class ClientHandler extends Thread {
    private final Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            output.println("Welcome to the chat server! Type 'exit' to quit.");

            String message;
            boolean waitingForName = false;
            boolean waitingForConfirmation = false; // NEW FLAG
            String clientName = "";

            while (true) {
                message = input.readLine(); // Read client message

                if (message == null || "exit".equalsIgnoreCase(message)) {
                    output.println("Goodbye!");
                    break;
                }

                System.out.println("Client: " + message);

                if (message.equalsIgnoreCase("hi") || message.equalsIgnoreCase("hi server")) {
                    output.println("Hello, welcome to Dream-Dev TCP server! What is your name?");
                    waitingForName = true;
                }
                else if (waitingForName) {
                    clientName = message; // Store client's name
                    output.println("Hello " + clientName + ", I hope you enjoy your training today!");
                    waitingForName = false; // Stop expecting name
                    waitingForConfirmation = true; // Now expect "yes"
                }
                else if (waitingForConfirmation && (message.equalsIgnoreCase("yes") || message.equalsIgnoreCase("yes i am"))) {
                    output.println("Enjoy your experience and make great use of it...Thank you!");
                    waitingForConfirmation = false; // Reset flag after responding
                }
                else {
                    output.println("I'm not sure what you mean. Try saying 'Hi' to start!");
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                socket.close();
                System.out.println("Client disconnected.");
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
        }
    }
}