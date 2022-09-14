package com.demo.reference;

import java.lang.ref.SoftReference;

/**
 * 软引用示例代码
 * -Xmx10M -XX:+PrintGCDetails
 */
public class SoftReferenceDemo {
    public static void main(String[] args) {
        Object strongReference = new Object();
        SoftReference<Object> softReference = new SoftReference<>(strongReference);

        System.out.println("强引用对象hash值：" + strongReference);
        System.out.println("软引用对象hash值：" + softReference.get());

        try {
            // 释放强引用
            strongReference = null;
            // 创建一个7M的对象，使得触发GC
            byte[] bytes = new byte[7 * 1024 * 1024];
        } catch (Error error) {
            error.printStackTrace();
        } finally {
            System.out.println("********GC后的情况********");
            System.out.println("强引用对象hash值：" + strongReference);
            System.out.println("软引用对象hash值：" + softReference.get());
        }
    }
}
