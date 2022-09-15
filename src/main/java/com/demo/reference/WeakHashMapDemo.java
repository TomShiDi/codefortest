package com.demo.reference;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * 弱引用key的HashMap
 * 当key为null后，整个entry将被removed
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        Map<String, String> weakHashMap = new WeakHashMap<>();
        String name = new String("name");
        String age = new String("age");
        weakHashMap.put(name, "tomshidi");
        weakHashMap.put(age, "2");

        System.out.println(weakHashMap);

        // 移除name变量的强引用
        name = null;
        // 不一定立即执行GC
        System.gc();

        System.out.println("*********************");
        System.out.println(weakHashMap);
    }
}
