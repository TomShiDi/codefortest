package com.demo.lockTest;

import java.util.concurrent.TimeUnit;

/**
 * 死锁用例
 * 【jps】命令获取java进程id
 * 【jstack 进程id】获取jvm中线程栈情况
 * @author tomshidi
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB), "线程1").start();
        new Thread(new HoldLockThread(lockB, lockA), "线程2").start();
    }

    static class HoldLockThread implements Runnable {

        private String lockA;

        private String lockB;

        public HoldLockThread(String lockA, String lockB) {
            this.lockA = lockA;
            this.lockB = lockB;
        }

        @Override
        public void run() {
            System.out.printf("【%s】等待锁%s\n", Thread.currentThread().getName(), lockA);
            synchronized (lockA) {
                System.out.printf("【%s】：持有锁%s\n", Thread.currentThread().getName(), lockA);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("【%s】等待锁%s\n", Thread.currentThread().getName(), lockB);
                synchronized (lockB) {
                    System.out.printf("【%s】持有锁%s\n", Thread.currentThread().getName(), lockB);
                }
            }
        }
    }

}
