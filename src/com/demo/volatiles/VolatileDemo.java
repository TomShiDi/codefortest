package com.demo.volatiles;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class VolatileDemo {

    public static void main(String[] args) {
        atomic();
    }

    public static void atomic() {
        Data data = new Data();
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    data.addByPreValueAtomic(1);
                }
                countDownLatch.countDown();
            }, "thread" + i).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("【%s】：主线程获取到的最终number值：%s\n", Thread.currentThread(), data.atomicNumber.get());
    }

    /**
     * volatile不能保证原子性
     */
    public static void volatileCannotEnsureAtomic() {
        Data data = new Data();
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    data.addByPreValue();
                }
                countDownLatch.countDown();
            }, "thread" + i).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("【%s】：主线程获取到的最终number值：%s\n", Thread.currentThread(), data.number);
    }

    /**
     * volatile可见性
     */
    public static void readableByVolatile() {
        Data data = new Data();
        new Thread(() -> {
            System.out.printf("【%s】：come in\n", Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.addTo60();
            System.out.printf("【%s】：updated number to %d\n", Thread.currentThread().getName(), data.number);
        }, "AAA").start();

        // 当data.number没有volatile修饰时，主线程会一直卡在这儿，主线程无法获取到变更
        while (data.number == 0) {

        }

        System.out.printf("【%s】：主线程执行完毕，主线程获取到的number为：%d\n", Thread.currentThread().getName(), data.number);
    }

    static class Data {
        volatile int number = 0;

        AtomicInteger atomicNumber = new AtomicInteger(0);

        public void addTo60() {
            this.number = 60;
        }

        public void addByPreValue() {
            this.number++;
//            this.number = this.number + 1;
        }

        public void addByPreValueAtomic() {
            atomicNumber.incrementAndGet();
        }

        public void addByPreValueAtomic(int addNumber) {
            int currValue = 0;
            do {
                currValue = this.atomicNumber.get();
            } while (!this.atomicNumber.compareAndSet(currValue, currValue + addNumber));
        }
    }
}

