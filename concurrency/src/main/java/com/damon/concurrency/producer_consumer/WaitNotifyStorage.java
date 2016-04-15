package com.damon.concurrency.producer_consumer;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 仓库类Storage实现缓冲区
 * 当缓冲区为空时，不能取；当缓冲区满时，不能填
 * Created by dongjun.wei on 16/4/4.
 */
public class WaitNotifyStorage implements Storage{

    //仓库最大存储容量
    private final int MAX_SIZE = 100;

    //仓库存储的载体
    private final List<Object> list = Lists.newLinkedList();

    /**
     * 生产产品
     * @param num 生产个数
     */
    public void produce(int num) {
        // 同步代码段
        synchronized (list) {
            // 如果仓库剩余容量不足
            while (list.size()+num > MAX_SIZE) {
                System.out.println("【要生产的产品数量】:" + num + "\t【库存量】:"
                        + list.size() + "\t暂时不能执行生产任务!");
                try {
                    // 由于条件不满足，生产阻塞
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 生产条件满足情况下，生产num个产品
            for (int i = 0; i < num; ++i) {
                list.add(new Object());
            }

            System.out.println("【已经生产产品数】:" + num + "\t【现仓储量为】:" + list.size());

            list.notifyAll();
        }
    }

    /**
     * 消费产品
     * @param num 消费个数
     */
    public void consume(int num) {
        synchronized (list) {
            while (list.size() < num) {
                System.out.println("【要消费的产品数量】:" + num + "\t【库存量】:"
                        + list.size() + "\t暂时不能执行生产任务!");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < num; ++i) {
                list.remove(0);
            }

            System.out.println("【已经消费产品数】:" + num + "\t【现仓储量为】:" + list.size());

            list.notifyAll();
        }
    }
}
