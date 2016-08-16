package com.damon.concurrency.simulation.bankteller;

/**
 * Created by damon on 16/8/16.
 */
public class Customer {
    private final int serviceTime;

    public Customer(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "[" + serviceTime +
                ']';
    }
}
