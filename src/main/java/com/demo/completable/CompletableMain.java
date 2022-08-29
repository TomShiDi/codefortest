package com.demo.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author TomShiDi
 * @description
 * @date 2021/10/30 19:09
 **/
public class CompletableMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> System.out.println("runAsync方法不能有返回值"));
//        completableFuture1
//                .whenComplete((t, u) -> {
//            System.out.println("无返回值的异步回调数据：t=" + t + ",u=" + u);
//        }).get();

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "Hello World");
        completableFuture2
                .whenComplete((t, u) -> System.out.println("有返回值的异步回调数据：t=" + t + ",u=" + u))
                .get();
    }
}
