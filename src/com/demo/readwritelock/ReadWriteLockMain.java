package com.demo.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author TomShiDi
 * @description
 * @date 2021/10/5 17:40
 **/
public class ReadWriteLockMain {
    public static void main(String[] args) throws InterruptedException {
        AtomicOperation atomicOperation = new AtomicOperation();
        new Thread(() -> atomicOperation.atomicGet("1"),"GET1").start();
        TimeUnit.SECONDS.sleep(3);
        new Thread(() -> atomicOperation.atomicPut("1", 1), "PUT1").start();
    }

    static class AtomicOperation {
        private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private Map<String, Integer> dataMap = new HashMap<>(8);

        public void atomicPut(String key, Integer value) {
            System.out.printf("【%s】：获取【写锁前】\n", Thread.currentThread().getName());
            readWriteLock.writeLock().lock();
            System.out.printf("【%s】：获取【写锁后】\n", Thread.currentThread().getName());
            try {
                dataMap.put(key, value);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                readWriteLock.writeLock().unlock();
                System.out.printf("【%s】：释放写锁\n", Thread.currentThread().getName());
            }
        }

        public Integer atomicGet(String key) {
            System.out.printf("【%s】：获取【读锁前】\n", Thread.currentThread().getName());
            readWriteLock.readLock().lock();
            System.out.printf("【%s】：获取【读锁后】\n", Thread.currentThread().getName());
            Integer result = null;
            try {
                result = dataMap.get(key);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 如果不释放读锁，那写锁线程将永远无法获取锁
//                readWriteLock.readLock().unlock();
//                System.out.printf("【%s】：释放读锁\n", Thread.currentThread().getName());
            }
            return result;
        }
    }
}
