package com.damon.concurrency.producer_consumer;

/**
 * Created by dongjun.wei on 16/4/4.
 */
public class Producer extends Thread {

    private Storage storage;

    private int num;

    public Producer(Storage storage, int num) {
        this.storage = storage;
        this.num = num;
    }
    @Override
    public void run() {
        storage.produce(num);
    }
}
