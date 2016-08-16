package com.damon.concurrency.wait_notify;

/**
 * 两个线程，一个打印1-100的奇数，一个打印1-100的偶数
 * 要求：线程1打印5个之后，线程2开始打印，线程2打印5个之后，线程1再开始打印，以此循环。
 * Created by dongjun.wei on 16/4/4.
 */
public class OddEven {

    //state==1表示线程1开始打印，state==2表示线程2开始打印
    private static int state = 1;

    private static int num1 = 1;
    private static int num2 = 2;

    public static void main(String[] args) {
        final Object oddEven = new Object();
        new Thread(() -> {
            while (num1 < 100) {
                //两个线程都用t对象作为锁，保证每个交替期间只有一个线程在打印
                synchronized (oddEven) {
                    // 如果state!=1, 说明此时尚未轮到线程1打印, 线程1将调用t的wait()方法, 直到下次被唤醒
                    if (state != 1) {
                        try {
                            oddEven.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 当state=1时, 轮到线程1打印5次数字
                    for (int i = 0; i < 5; ++i) {
                        System.out.println(num1);
                        num1 += 2;
                    }
                    // 线程1打印完成后, 将state赋值为2, 表示接下来将轮到线程2打印
                    state = 2;
                    // notifyAll()方法唤醒在t上wait的线程2, 同时线程1将退出同步代码块, 释放t锁
                    oddEven.notifyAll();
                }
            }
        }).start();

        new Thread(() -> {
            while (num2 < 100) {
                synchronized (oddEven) {
                    if (state != 2) {
                        try {
                            oddEven.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 0; i < 5; ++i) {
                        System.out.println(num2);
                        num2 += 2;
                    }
                    state = 1;
                    oddEven.notifyAll();
                }
            }
        }).start();
    }
}
