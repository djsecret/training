package com.damon.network.multhread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by damon on 16/6/15.
 */
public class MulServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2000);
        while (true) {
            Socket socket = serverSocket.accept();
            new SocketThread(socket);
        }
    }

}

class SocketThread extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    SocketThread(Socket socket) throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Client(" + getName() + ") come in...");
        start();
    }

    @Override
    public void run() {
        try {
            String line = reader.readLine();
            while (!line.equals("q")) {
                System.out.println("Client(" + getName() + ") say: " + line);
                writer.println("continue, Client(" + getName() + ")!");
                writer.flush();
                line = reader.readLine();
            }
            writer.println("bye, Client(" + getName() + ")!");
            writer.flush();
            System.out.println("Client(" + getName() + ") exit!");
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
