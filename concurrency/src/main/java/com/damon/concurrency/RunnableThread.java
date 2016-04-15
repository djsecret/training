package com.damon.concurrency;

/**
 * Created by dongjun.wei on 16/3/24.
 */
public class RunnableThread implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("Runnable thread " + Thread.currentThread().getName() + " is running.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
