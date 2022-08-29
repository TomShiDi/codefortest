package com.demo.collections;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 集合不安全问题
 */
public class CollectionNotSafeDemo {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = Stream.of("1", "2", "3").collect(Collectors.toList());

        // ConcurrentModificationException 扩容也会增加modifyCount，toString使用到了Iterator，多线程下会出现modifyCount不一致的问题
        // 增强for循环中使用集合本身删除元素报错的根本原因也就是Iterator中的modifyCount与集合本身的不一致
        for (int j = 0; j < 100; j++) {
            new Thread(() -> {
//                for (int i = 0; i < 1000; i++) {
//                    list.add(i + "");
//                }
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add("ssss");
                System.out.println(list);
            }, j + "").start();
        }

        // 处理方案1：Vector
        // 处理方案2：Collections.synchronizedList()
        // 处理方案3：CopyOnWriteArrayList 读写分离，写操作加排它锁，写操作的是复制出来的新数组。

        TimeUnit.SECONDS.sleep(50);
    }
}
