package com.demo.lockTest;

import java.util.concurrent.locks.Lock;

/**
 * @Author TomShiDi
 * @Since 2019/5/31
 * @Version 1.0
 */
public class LaunchMain {

    private int count;

    private static boolean flag = true;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    System.out.println(Thread.currentThread().getName() + "正在运行");
                }
            }
        }, "任务线程").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = false;
            }
        },"控制线程").start();

    }
}
