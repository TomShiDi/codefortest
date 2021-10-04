package com.demo.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author TomShiDi
 * @description
 * @date 2021/10/4 15:32
 **/
public class SemaphoreMain {

    public static void main(String[] args) {
        // 初始化三个车位
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    // 占用车位
                    semaphore.acquire();
                    System.out.printf("【%s】：进入了车位\n", Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
                    System.out.printf("【%s】：离开了车位\n", Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 释放车位
                    semaphore.release();
                }
            }, "车辆" + i).start();
        }
    }
}
