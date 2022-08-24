package com.demo.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题处理
 */
public class AtomicStampReferenceDemo {

    private static AtomicStampedReference<DemoData> atomicStampedReference = new AtomicStampedReference<>(new DemoData("", 1), 1);

    public static void main(String[] args) throws InterruptedException {
        DemoData zhangsanData = new DemoData("张三", 3);
        DemoData lisiData = new DemoData("李四", 4);
        atomicStampedReference.set(zhangsanData, 1);
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            boolean result = atomicStampedReference.compareAndSet(zhangsanData, lisiData, stamp, stamp + 1);
            System.out.printf("【%s】：版本号：%d，交换结果：%s\n", Thread.currentThread().getName(), atomicStampedReference.getStamp(), result);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stamp = atomicStampedReference.getStamp();
            result = atomicStampedReference.compareAndSet(lisiData, zhangsanData, stamp, stamp + 1);
            System.out.printf("【%s】：版本号：%d，交换结果：%s\n", Thread.currentThread().getName(), atomicStampedReference.getStamp(), result);
        },"发生ABA的线程").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(zhangsanData, lisiData, stamp, stamp + 1);
            System.out.printf("【%s】：版本号：%d，交换结果：%s\n", Thread.currentThread().getName(), stamp, result);
        },"落后版本的线程").start();
        TimeUnit.SECONDS.sleep(10);
    }

    static class DemoData {
        private String name;
        private int age;

        public DemoData(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
