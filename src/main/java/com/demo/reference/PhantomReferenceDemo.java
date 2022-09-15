package com.demo.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;

/**
 * 虚引用使用用例
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1, referenceQueue);

        System.out.println("强引用为" + o1);
        // 虚引用get永远返回null，只有在回收前被加到ReferenceQueue中才能获取
        System.out.println("虚引用为" + phantomReference.get());
        System.out.println("引用队列内容：" + referenceQueue.poll());

        // 移除强引用
        o1 = null;
        // 不一定立即执行GC
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("*********************");
        System.out.println("强引用为" + o1);
        System.out.println("虚引用为" + phantomReference.get());
        System.out.println("引用队列内容：" + referenceQueue.poll());
    }
}
