package com.demo.lockTest;

/**
 * @Author TomShiDi
 * @Since 2019/5/31
 * @Version 1.0
 */
public class BaseRunnable implements Runnable{

    private static int count = 0;

    private String option;

    private boolean target = false;

    public BaseRunnable(String option) {
        this.option = option;
    }

    @Override
    public void run() {

        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count == 20) {
                target = true;
            }
            System.out.println("thread name: " + Thread.currentThread().getName() + " count: " + count);
            if ("sub".equals(option)) {
                System.out.println("method: sub");
                sub();
            }
            if ("add".equals(option)) {
                System.out.println("method: add");
                add();
            }
            if ("read".equals(option)) {
                System.out.println("read: " + count);
            }

            if (target) {
                System.out.println("开关已经打开");
            }
        }
    }

    private static void sub() {
        count--;
    }

    private static void add() {
        count++;
    }
}
