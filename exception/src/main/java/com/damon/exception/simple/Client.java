package com.damon.exception.simple;

/**
 * Created by damon on 16/6/16.
 */
public class Client {

    public void a() {
        System.out.println("a");
        throw new AppException(1, "a");
    }

    public void b() {
        System.out.println("b");
        throw new AppException("b", 1, "b");
    }

    public static void main(String[] args) {
        Client client = new Client();

        //client.a();

        client.b();
    }
}
