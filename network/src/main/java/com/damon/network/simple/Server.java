package com.damon.network.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by damon on 16/6/15.
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(2000);
            while (true) {
                Socket socket = serverSocket.accept();

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

                String line;
                do {
                    line = reader.readLine();
                    System.out.println("Client say:" + line);
                    printWriter.println("hello Client, I am Server!" + line);
                    printWriter.flush();

                } while (line != null);

                System.out.println("q!");

                printWriter.close();
                reader.close();
                socket.close();
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
