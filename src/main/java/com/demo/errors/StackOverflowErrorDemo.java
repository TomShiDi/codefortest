package com.demo.errors;

/**
 * 栈溢出出现的场景
 */
public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        infinityFunc();
    }

    public static void infinityFunc() {
        infinityFunc();
    }
}
