package com.demo.reference;

import java.lang.ref.WeakReference;

/**
 * 弱引用用例
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);

        System.out.println("强引用对象hash值：" + o1);
        System.out.println("软引用对象hash值：" + weakReference.get());

        o1 = null;
        // 不一定会立即执行GC
        System.gc();
        System.out.println("*****************************");
        System.out.println("强引用对象hash值：" + o1);
        System.out.println("软引用对象hash值：" + weakReference.get());
    }
}
