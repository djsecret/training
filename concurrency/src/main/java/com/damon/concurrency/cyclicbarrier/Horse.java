package com.damon.concurrency.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by damon on 16/8/16.
 */
public class Horse implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0;
    private static Random random = new Random(47);
    private CyclicBarrier barrier;

    Horse(CyclicBarrier b) {
        barrier = b;
    }

    public synchronized int getStrides() {
        return strides;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    strides += random.nextInt(3);
                }
                barrier.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String toString() {

        return "Horse{" +
                "id=" + id +
                '}';
    }

    public String tricks() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < getStrides(); ++i) {
            stringBuilder.append("*");
        }
        stringBuilder.append(id);
        return stringBuilder.toString();

    }



}
