package com.demo.queue;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列使用Demo
 */
public class BlockingQueueDemo {

    private static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

    public static void main(String[] args) throws InterruptedException {
        blockApi();
    }

    public static void blockApi() throws InterruptedException {
        new Thread(() -> {
            // 队满时阻塞
            try {
                queue.put("1");
                System.out.println("添加第一个元素到队尾：1");
                queue.put("2");
                System.out.println("添加第二个元素到队尾：2");
                queue.put("3");
                System.out.println("添加第三个元素到队尾：3");
                queue.put("4");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("添加元素到队尾：4");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "生产者").start();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("输入任意指令移除队首元素：");
            scanner.nextLine();
            System.out.println("移除的元素为：" + queue.poll(3, TimeUnit.SECONDS));
        }
    }

    public static void specialValueApi() {
        // 队列满时插入失败，返回false
        System.out.println("添加第一个元素到队尾：" + queue.offer("1"));
        System.out.println("添加第二个元素到队尾：" + queue.offer("2"));
        System.out.println("添加第三个元素到队尾：" + queue.offer("3"));
        System.out.println("添加第四个元素到队尾：" + queue.offer("4"));

        // 获取队首元素，不移除
        System.out.println("取出队首第一个元素：" + queue.peek());

        // 获取并移除队首元素，队列为空时返回null
        System.out.println("取出并移除队首元素：" + queue.poll());
        System.out.println("取出并移除队首元素：" + queue.poll());
        System.out.println("取出并移除队首元素：" + queue.poll());
        System.out.println("取出并移除队首元素：" + queue.poll());
    }

    public static void exceptionApi() {
        // 队列满时抛异常
        queue.add("1");
        queue.add("2");
        queue.add("3");
//        queue.add("4");

        // 队首移除一个元素，队列空时抛异常
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
    }
}
