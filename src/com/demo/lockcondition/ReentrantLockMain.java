package com.demo.lockcondition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author TomShiDi
 * @description
 * @date 2021/9/25 14:56
 **/
public class ReentrantLockMain {
    public static void main(String[] args) throws InterruptedException {
        AtomicOperation atomicOperation = new AtomicOperation();
        new Thread(() -> atomicOperation.operation(), "A").start();
        new Thread(() -> atomicOperation.operation(), "B").start();
        new Thread(() -> atomicOperation.operation(), "C").start();
        TimeUnit.SECONDS.sleep(5);
        atomicOperation.signalAll();
    }

    static class AtomicOperation {
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void operation() {
            lock.lock();
            try {
                System.out.printf("%s：等待【开始】\n", Thread.currentThread().getName());
                condition.await();
                System.out.printf("%s：等待【结束】\n", Thread.currentThread().getName());
//                System.out.printf("%s：单个condition唤醒所有线程\n", Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void signalAll() {
            lock.lock();
            try {
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }
}
