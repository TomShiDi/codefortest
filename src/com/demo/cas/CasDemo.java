package com.demo.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CasDemo {

    public static void main(String[] args) {
        int threadNum = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        DemoData demoData = new DemoData();
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                for (int j = 0; j < 200; j++) {
                    demoData.casAdd(1);
                }
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("【%s】：主线程获取到的值为 %d\n", Thread.currentThread().getName(), demoData.atomicInteger.get());
    }

    static class DemoData {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        public void casAdd(int update) {
            int expect = 0;
            do {
                expect = atomicInteger.get();
            } while (!atomicInteger.compareAndSet(expect, expect + update));
        }
    }
}
