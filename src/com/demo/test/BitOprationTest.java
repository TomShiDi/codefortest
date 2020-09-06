package com.demo.test;

import org.junit.Test;

public class BitOprationTest {

    @Test
    public void doTest() {
        int positiveNum = 0x1;
        int negativeNum = 0x90000001;
        System.out.println("正数表示: " + Integer.toBinaryString(positiveNum));
        System.out.println("正数>>  : " + Integer.toBinaryString(positiveNum >> 1));
        System.out.println("正数>>> : " + Integer.toBinaryString(positiveNum >>> 1));
        System.out.println("负数表示: " + Integer.toBinaryString(negativeNum));
        System.out.println("负数>>  : " + Integer.toBinaryString(negativeNum >> 1));
        System.out.println("负数>>> : " + Integer.toBinaryString(negativeNum >>> 1));
        System.out.println("二进制输出测试: "+Integer.toString(0x7fffffff));
    }
}
