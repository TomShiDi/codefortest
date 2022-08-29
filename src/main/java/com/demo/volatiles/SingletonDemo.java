package com.demo.volatiles;

/**
 * 单例模式6种形式：懒汉、恶汉、双重校验锁、枚举
 */
public class SingletonDemo {

    /** 不加volatile，由于指令重排，此处的实例可能还未实例化完毕，就被其他线程拿去使用了。
     * 1. memory = allocate() 分配对象内存空间
     * 2. initialize(memory) 实例化对象
     * 3. instance = memory 设置instance变量指向分配的内存
     * 由于步骤2与步骤3不存在数据依赖，由于指令重排，可能出现132的情况
     */
    private volatile static SingletonDemo INSTANCE;

    private SingletonDemo() {
        System.out.printf("【%s】：SingletonDemo构造方法被执行\n", Thread.currentThread().getName());
    }

    /**
     * 普通模式，有多线程数据安全问题
     * @return
     */
    public static SingletonDemo getInstance() {
        if (SingletonDemo.INSTANCE == null) {
            SingletonDemo.INSTANCE = new SingletonDemo();
        }
        return SingletonDemo.INSTANCE;
    }

    /**
     * 双重校验锁模式
     * @return
     */
    public static SingletonDemo getInstanceDoubleCheckLock() {
        // 拦截大部分线程，保证大多数用户的请求执行效率
        if (SingletonDemo.INSTANCE == null) {
            // 对漏网之鱼做同步处理
            synchronized (SingletonDemo.class) {
                // 同步块中单线程校验，不会误判
                if (SingletonDemo.INSTANCE == null) {
                    SingletonDemo.INSTANCE = new SingletonDemo();
                }
            }
        }
        return SingletonDemo.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, "线程" + i).start();
        }
    }
}
