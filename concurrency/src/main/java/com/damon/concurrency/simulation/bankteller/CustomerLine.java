package com.damon.concurrency.simulation.bankteller;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by damon on 16/8/16.
 */
public class CustomerLine extends ArrayBlockingQueue<Customer> {
    public CustomerLine(int maxLineSize) {
        super(maxLineSize);
    }

    public String toString() {
        if (this.size() == 0) {
            return "[Empty]";
        }
        StringBuilder result = new StringBuilder();
        for (Customer customer : this) {
            result.append(customer);
        }
        return result.toString();
    }
}
