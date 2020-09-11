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
    public static void main(String[] args) {
        Executor executor = new ThreadPoolExecutor(10,
                10,
                100,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                new TomThreadFactory("zzz"));
        for (int i = 0; i < 10; i++) {
            final int j = i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + ":\t" + j);
            });
        }
    }
}
