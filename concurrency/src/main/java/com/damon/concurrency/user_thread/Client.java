package com.damon.concurrency.user_thread;

/**
 * Created by dongjun.wei on 16/3/28.
 */
public class Client {

    public static void main(String[] args) {
        Thread thread = new Thread(new UserThread());
        thread.setName("UserThread");
        thread.setDaemon(false);
        thread.start();
        //System.exit(0);
        System.out.println("System exit.");
    }
}
