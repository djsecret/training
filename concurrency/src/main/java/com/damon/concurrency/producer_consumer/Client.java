package com.damon.concurrency.producer_consumer;

/**
 * Created by dongjun.wei on 16/4/4.
 */
public class Client {

    public static void main(String[] args) {
        //Storage storage = new WaitNotifyStorage();
        //Storage storage = new BlockingQueueStorage();
        Storage storage = new LockStorage();
        Producer producer1 = new Producer(storage, 10);
        Producer producer2 = new Producer(storage, 10);
        Producer producer3 = new Producer(storage, 10);
        Producer producer4 = new Producer(storage, 10);
        Producer producer5 = new Producer(storage, 10);
        Producer producer6 = new Producer(storage, 10);
        Producer producer7 = new Producer(storage, 80);

        Consumer consumer1 = new Consumer(storage, 50);
        Consumer consumer2 = new Consumer(storage, 20);
        Consumer consumer3 = new Consumer(storage, 30);

        consumer1.start();
        consumer2.start();
        consumer3.start();
        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        producer5.start();
        producer6.start();
        producer7.start();
    }
}
