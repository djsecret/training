package com.damon.concurrency.producer_consumer;

/**
 * Created by dongjun.wei on 16/4/4.
 */
public interface Storage {
    void produce(int num);

    void consume(int num);
}
