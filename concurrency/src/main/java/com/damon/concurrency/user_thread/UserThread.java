package com.damon.concurrency.user_thread;

/**
 * Created by dongjun.wei on 16/3/28.
 */
public class UserThread implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("user thread is running.");
//            try {
//                //Thread.sleep(5000);
//                System.out.println("addressbook thread is still running.");
//                //Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
