package com.demo.lockcondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author TomShiDi
 * @description
 * @date 2021/9/19 17:25
 **/
public class LockConditionMain {
    public static void main(String[] args) {
        AtomicOperation atomicOperation = new AtomicOperation();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                atomicOperation.increment();
            }
        }, "add1").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                atomicOperation.decrement();
            }
        }, "del1").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                atomicOperation.increment();
            }
        }, "add2").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                atomicOperation.decrement();
            }
        }, "del2").start();
    }

    private static class AtomicOperation {
        private int num = 0;

        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void increment() {
            lock.lock();
            try {
                while (num != 0) {
                    System.out.printf("%s：num!=0，被阻塞\n", Thread.currentThread().getName());
                    condition.await();
                }
                num++;
                System.out.printf("%s：当前num值为：%d\n", Thread.currentThread().getName(), num);
                // 唤醒其他线程
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

        public void decrement() {
            lock.lock();
            try {
                while (num != 1) {
                    System.out.printf("%s：num!=1，被阻塞\n", Thread.currentThread().getName());
                    condition.await();
                }
                num--;
                System.out.printf("%s：当前num值为：%d\n", Thread.currentThread().getName(), num);
                // 唤醒其他线程
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
