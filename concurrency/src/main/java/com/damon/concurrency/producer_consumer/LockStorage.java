package com.damon.concurrency.producer_consumer;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dongjun.wei on 16/4/5.
 */
public class LockStorage implements Storage {

    //仓库最大存储容量
    private final int MAX_SIZE = 100;

    //仓库存储的载体
    private final List<Object> list = Lists.newLinkedList();

    private final Lock lock = new ReentrantLock();

    private final Condition full = lock.newCondition();

    private final Condition empty = lock.newCondition();


    @Override
    public void produce(int num) {
        //获取锁
        lock.lock();

        while (list.size() + num > MAX_SIZE) {
            System.out.println("【要生产的产品数量】:" + num + "\t【库存量】:" + list.size()
                    + "\t暂时不能执行生产任务!");
            try {
                full.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 生产条件满足情况下，生产num个产品
        for (int i = 1; i <= num; ++i)
        {
            list.add(new Object());
        }

        System.out.println("【已经生产产品数】:" + num + "\t【现仓储量为】:" + list.size());

        // 唤醒其他所有线程
        full.signalAll();
        empty.signalAll();

        //释放锁
        lock.unlock();
    }

    @Override
    public void consume(int num) {
        // 获得锁  
        lock.lock();

        // 如果仓库存储量不足  
        while (list.size() < num)
        {
            System.out.println("【要消费的产品数量】:" + num + "\t【库存量】:" + list.size()
                    + "\t暂时不能执行生产任务!");
            try
            {
                // 由于条件不满足，消费阻塞  
                empty.await();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        // 消费条件满足情况下，消费num个产品  
        for (int i = 1; i <= num; ++i)
        {
            list.remove(0);
        }

        System.out.println("【已经消费产品数】:" + num + "\t【现仓储量为】:" + list.size());

        // 唤醒其他所有线程  
        full.signalAll();
        empty.signalAll();

        // 释放锁  
        lock.unlock();
    }
}
