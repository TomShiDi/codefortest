package com.demo.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * @author TomShiDi
 * @description
 * @date 2021/10/4 14:30
 **/
public class CountDownMain {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.printf("【%s】乘客上车了\n", Thread.currentThread().getName());
                countDownLatch.countDown();
            }, "乘客" + i).start();
        }

        countDownLatch.await();
        System.out.println("所有乘客都已上车，发车");
    }
}
