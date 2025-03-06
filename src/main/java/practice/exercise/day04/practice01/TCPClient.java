package practice.exercise.day04.practice01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {

    public static void main(String[] args) {

        try(Socket socket = new Socket("localhost", 6789)){
            System.out.println("Connected to TCP Server");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Hello from Dream-Dev TCP Client!!");

            //read server's response
            String serverResponse = in.readLine();
            System.out.println("Received from server: " + serverResponse);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
