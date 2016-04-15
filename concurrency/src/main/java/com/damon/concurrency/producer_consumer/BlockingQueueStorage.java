package com.damon.concurrency.producer_consumer;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by dongjun.wei on 16/4/4.
 */
public class BlockingQueueStorage implements Storage {
    //仓库最大存储容量
    private final int MAX_SIZE = 100;

    private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<Object>(MAX_SIZE);

    @Override
    public void produce(int num) {
        // 如果仓库剩余容量不足
        if (list.size()+num > MAX_SIZE) {
            System.out.println("【要生产的产品数量】:" + num + "\t【库存量】:"
                    + list.size() + "\t暂时不能执行生产任务!");
        }

        for (int i = 0; i < num; ++i) {
            try {
                list.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("【已经生产产品数】:" + num + "\t【现仓储量为】:" + list.size());
    }

    @Override
    public void consume(int num) {
        if (list.size() < num) {
            System.out.println("【要消费的产品数量】:" + num + "\t【库存量】:"
                    + list.size() + "\t暂时不能执行生产任务!");
        }

        for (int i = 0; i < num; ++i) {
            try {
                list.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("【已经消费产品数】:" + num + "\t【现仓储量为】:" + list.size());

    }
}
