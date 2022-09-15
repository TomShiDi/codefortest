package com.demo.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1, referenceQueue);

        System.out.println("强引用为" + o1);
        System.out.println("弱引用为" + weakReference.get());
        System.out.println("引用队列内容：" + referenceQueue.poll());

        // 移除强引用
        o1 = null;
        // 不一定立即执行GC
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("*********************");
        System.out.println("强引用为" + o1);
        System.out.println("弱引用为" + weakReference.get());
        System.out.println("引用队列内容：" + referenceQueue.poll());
    }
}
