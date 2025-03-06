package practice.exercise.day04.practice01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(6789)){

            System.out.println("TCP Server is running and waiting for client connection....");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // read data from client
            String clientMessage = in.readLine();
            System.out.println("Received from client: " + clientMessage);

            out.println("Hello from Dream-Dev TCP server");

            clientSocket.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
