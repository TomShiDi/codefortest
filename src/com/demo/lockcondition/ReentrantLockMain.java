package com.demo.lockcondition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁 ReentrantLock、synchronize
 * 同一线程中，外层方法获取锁后，内层方法自动获取锁。
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
        new Thread(() -> atomicOperation.operation(), "D").start();
        new Thread(() -> atomicOperation.operation(), "E").start();
        new Thread(() -> atomicOperation.operation(), "F").start();
        TimeUnit.SECONDS.sleep(5);
        atomicOperation.signalAll();
    }

    static class AtomicOperation {
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void operation() {
            // 有n个lock，就要有n个unlock
            lock.lock();
            try {
                System.out.printf("%s：等待【开始】\n", Thread.currentThread().getName());
                reLock();
                // 这里会释放自己持有的锁
                condition.await();
                System.out.printf("%s：等待【结束】\n", Thread.currentThread().getName());
//                System.out.printf("%s：单个condition唤醒所有线程\n", Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        private void reLock() {
            lock.lock();
            try {
                System.out.printf("%s：内层方法自动再次获取同一把锁\n", Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.printf("%s：内层方法解锁\n", Thread.currentThread().getName());
                lock.unlock();
            }
        }

        public void signalAll() {
            lock.lock();
            try {
                // 通知顺序为调用await()的顺序
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }
}
