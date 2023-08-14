package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static final Integer PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("New connection accepted");
            while (true) {
                try (Socket client = serverSocket.accept();
                     PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
                     BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream())))
                {
                    pw.println("Write your name: ");
                    final String name = br.readLine();
                    pw.println("Are you child? ");
                    final String isChild = br.readLine();
                    if (isChild.equalsIgnoreCase("yes")) {
                        pw.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                    } else if (isChild.equalsIgnoreCase("no")) {
                        pw.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!",name));
                    } else {
                        pw.println("Something going wrong");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}