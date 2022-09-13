package com.demo.runtime;

public class RuntimeApiDemo {
    public static void main(String[] args) {
        // Java虚拟机的内存总量
        long totalMemory = Runtime.getRuntime().totalMemory();
        // Java虚拟机试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();

        // 默认是机器内存的1/64
        System.out.println("Total Memory (-Xms) = " + totalMemory / (double) 1024 / 1024 + "MB");
        // 默认是机器内存的1/4
        System.out.println("Max Memory (-Xmx) = " + maxMemory / (double) 1024 / 1024 + "MB");
    }
}
