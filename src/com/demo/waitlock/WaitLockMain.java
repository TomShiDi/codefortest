package com.demo.waitlock;

import java.util.concurrent.TimeUnit;

/**
 * @author TomShiDi
 * @description
 * @date 2021/9/19 15:51
 **/
public class WaitLockMain {

    public static void main(String[] args) throws InterruptedException {
        TomClass tomClass = new TomClass();
        new Thread(() -> {
            try {
                tomClass.decrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        TimeUnit.SECONDS.sleep(2);
        // 唤醒上面释放锁的线程
        tomClass.notifySelf();
        // 保持主线程存活，让上面的线程执行完毕
        TimeUnit.SECONDS.sleep(1);
    }

    static class TomClass {

        private int num = 0;

        private int count = 1;

        public synchronized void decrement() throws InterruptedException {
            System.out.println("进入方法");
            while (num != 1) {
                System.out.println("执行wait前，当前轮次：" + count);
                this.wait();
                System.out.println("执行wait后，当前轮次：" + count);
                count++;
            }
            num--;
            System.out.printf("接着执行，当前轮次：%d，num值为：%d\n", count, num);
        }

        public synchronized void notifySelf() {
            this.notifyAll();
        }
    }
}
