package com.damon.network.simple;

import java.io.*;
import java.net.Socket;

/**
 * Created by damon on 16/6/15.
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 2000);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            BufferedReader sysReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line;
            do {
                line = sysReader.readLine();
                printWriter.println(line);
                printWriter.flush();
                System.out.println("Server say:" + reader.readLine());

            } while (!line.equals("q"));

            System.out.println("q");

            printWriter.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
