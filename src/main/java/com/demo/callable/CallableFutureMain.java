package com.demo.callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author TomShiDi
 * @description
 * @date 2021/10/1 20:55
 **/
public class CallableFutureMain {
    private final static Logger LOGGER = LoggerFactory.getLogger(CallableFutureMain.class);
    private static AtomicInteger count = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            LOGGER.info("进入callable方法");
            TimeUnit.SECONDS.sleep(5);
            return count.incrementAndGet();
        });
        new Thread(futureTask, "A").start();
        // 对于同一个FutureTask对象，即使被多个线程引用，也只会执行一次
        // 原因在于FutureTask的run方法中做了如下判断：1. 是否已被执行过；2. 是否被其他线程持有
        new Thread(futureTask, "B").start();
        while (!futureTask.isDone()) {
            System.out.printf("%s：任务还未完成\n", Thread.currentThread().getName());
            TimeUnit.MICROSECONDS.sleep(200);
        }
        System.out.printf("%s：任务执行完毕，结果是：%d\n",
                Thread.currentThread().getName(),
                futureTask.get());
    }
}
