package com.damon.concurrency;

/**
 * Created by dongjun.wei on 16/3/24.
 */
public class Client {
    public static void main(String[] args) {
        RunnableThread runnableThread = new RunnableThread();

        Thread thread1 = new Thread(runnableThread, "1");
        Thread thread2 = new Thread(runnableThread, "2");

        thread1.start();
        thread2.start();
    }
}
