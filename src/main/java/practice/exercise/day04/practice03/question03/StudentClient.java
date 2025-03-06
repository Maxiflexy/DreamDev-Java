package practice.exercise.day04.practice03.question03;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class StudentClient {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 8080);

             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println(in.readLine()); // Read welcome message

            while (true) {
                System.out.print("Enter student name to search (or 'exit' to quit): ");
                String name = scanner.nextLine();
                out.println(name);

                String response = in.readLine();
                System.out.println(response);

                if ("Goodbye!".equals(response)) break;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

