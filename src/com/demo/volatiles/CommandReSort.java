package com.demo.volatiles;

/**
 * 多线程中线程交替执行，由于编译器优化重排的存在，两个线程中使用的变量无法保证一致性，导致结果无法预测
 */
public class CommandReSort {
    int a = 0;
    boolean flag = false;

    /**
     * 未加volatile时由于单个线程中变量赋值没有数据依赖
     * 多线程下可能出现的顺序为
     * 线程一执行：语句2
     * 线程二执行：语句3
     * 线程一执行：语句1
     */
    public void method1() {
        // 语句1
        a = 1;
        // 语句2
        flag = true;
    }

    public void method2() {
        if (flag) {
            // 语句3
            a = a + 5;
            System.out.println("value：" + a);
        }
    }

    public static void main(String[] args) {

    }

    public static void func() {
        int x = 11; //语句1
        int y = 12; //语句2
        x = x + 5;  //语句3
        y = x * x;  //语句4

        /**
         * 指令重排需要保证数据的依赖性
         * 可能的顺序有
         * 1234
         * 1324
         * 2134
         */

        /**
         * 单线程中指令重排对结果没有影响
         * 多线程时因为只保证单个线程中指令数据的依赖性，会导致多个线程执行完的最终结果不一致。
         */
    }
}
