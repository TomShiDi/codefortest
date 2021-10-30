package com.demo.executortest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author TomShiDi
 * @Since 2020/9/11
 * @Version 1.0
 */
public class ThreadPoolMain {
    public static void main(String[] args) throws InterruptedException {
        Executor executor = new ThreadPoolExecutor(10,
                10,
                100,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                new TomThreadFactory("zzz"),
                new TomRejectedExecutionHandler());
        for (int i = 0; i < 30; i++) {
            final int j = i;
//            TimeUnit.SECONDS.sleep(2);
            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":\t" + j);
            });
        }
    }
}
