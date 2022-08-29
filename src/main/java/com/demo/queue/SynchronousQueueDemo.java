package com.demo.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(SynchronousQueueDemo.class);

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        BlockingQueue<String> queue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                queue.put("a");
                LOGGER.info("添加元素到SynchronousQueue队列：{}", "a");

                queue.put("b");
                LOGGER.info("添加元素到SynchronousQueue队列：{}", "b");

                queue.put("c");
                LOGGER.info("添加元素到SynchronousQueue队列：{}", "c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }

        }, "生产者").start();

        new Thread(() -> {
            try {
                LOGGER.info("获取SynchronousQueue队列元素：{}", queue.take());
                TimeUnit.SECONDS.sleep(2);
                LOGGER.info("获取SynchronousQueue队列元素：{}", queue.take());
                TimeUnit.SECONDS.sleep(2);
                LOGGER.info("获取SynchronousQueue队列元素：{}", queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }, "消费者").start();

        countDownLatch.await();
        LOGGER.info("消费者取完了生产者生产的数据");
    }
}
