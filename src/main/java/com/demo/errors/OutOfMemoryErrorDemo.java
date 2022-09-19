package com.demo.errors;


/**
 * OOM异常 Java heap space
 * 堆内存溢出
 */
public class OutOfMemoryErrorDemo {
    public static void main(String[] args) {

    }

    public static void oomJavaHeapSpace() {
        // -Xmx10M
        byte[] byteArr = new byte[20 * 1024 * 1024];
    }
}
