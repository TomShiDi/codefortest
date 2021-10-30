package com.demo.executortest;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author TomShiDi
 * @description
 * @date 2021/10/30 16:08
 **/
public class TomRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("自定义拒绝策略：线程池核心线程耗尽，排队队列耗尽。因此本次执行被终止废弃");
    }
}
