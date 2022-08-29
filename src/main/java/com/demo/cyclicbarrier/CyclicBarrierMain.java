package com.demo.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 等待线程数达到设定值后，所有线程同时唤醒
 * @author TomShiDi
 * @description
 * @date 2021/10/4 14:54
 **/
public class CyclicBarrierMain {
    private static int count = 10;
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count,
                () -> System.out.printf("【%s】：乘客均已上车\n", Thread.currentThread().getName()));
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                System.out.printf("【%s】上车\n", Thread.currentThread().getName());
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.printf("【%s】线程已被唤醒\n", Thread.currentThread().getName());
            }, "乘客" + i).start();
        }
    }
}
