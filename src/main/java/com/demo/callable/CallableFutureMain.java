package com.demo.callable;

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
    private static AtomicInteger count = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(5);
            return count.incrementAndGet();
        });
        new Thread(futureTask, "A").start();
        while (!futureTask.isDone()) {
            System.out.printf("%s：任务还未完成\n", Thread.currentThread().getName());
            TimeUnit.MICROSECONDS.sleep(200);
        }
        System.out.printf("%s：任务执行完毕，结果是：%d\n",
                Thread.currentThread().getName(),
                futureTask.get());
    }
}
