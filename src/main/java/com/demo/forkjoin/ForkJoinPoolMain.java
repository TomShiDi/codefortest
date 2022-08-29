package com.demo.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author TomShiDi
 * @description
 * @date 2021/10/30 18:17
 **/
public class ForkJoinPoolMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TomTask tomTask = new TomTask(0, 100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(tomTask);
        Integer result = forkJoinTask.get();
        System.out.println("拆分合并计算结果是：" + result);
    }

    public static class TomTask extends RecursiveTask<Integer> {

        private int stepLength = 10;

        private int start = 0;

        private int end = 0;

        public TomTask(int start, int end) {
            this.start = start;
            this.end = end;
            if (end <= start) {
                throw new RuntimeException("起始值不能大于等于截止值");
            }
        }

        @Override
        protected Integer compute() {
            int result = 0;
            // 控制start与end的差值小于步长
            if (end - start <= stepLength) {
                for (int i = start; i <= end; i++) {
                    result = result + i;
                }
            } else {
                // 寻找中间值
                int middle = (start + end) / 2;
                System.out.printf("start:%d  end:%d  middle:%d\n", start, end, middle);
                // 计算左边
                TomTask tomTask1 = new TomTask(start, middle);
                // 计算右边
                TomTask tomTask2 = new TomTask(middle + 1, end);
                // 调用拆分
                tomTask1.fork();
                tomTask2.fork();
                // 调用合并
                result = tomTask1.join() + tomTask2.join();
            }
            return result;
        }
    }
}
