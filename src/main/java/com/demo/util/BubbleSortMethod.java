package com.demo.util;

/**
 * 冒泡排序
 *
 * @Author TomShiDi
 * @Since 2020/9/2
 * @Version 1.0
 */
public class BubbleSortMethod {

    public static void sort(int[] sources, boolean leftIsBiggest, boolean isUsingBitCalculate) {
        /**
         * 从大到小排列
         */
        if (leftIsBiggest) {
            for (int i = sources.length - 1; i >= 0; i--) {
                for (int j = 0; j <= i - 1; j++) {
                    if (sources[j] < sources[j + 1]) {
                        if (isUsingBitCalculate) {
                            xorSwap(sources, j, j + 1);
                        } else {
                            additionSwap(sources, j, j + 1);
                        }
                    }
                }
            }
        } else {
            /**
             * 从小到大排列
             */
            for (int i = sources.length - 1; i >= 0; i--) {
                for (int j = 0; j <= i - 1; j++) {
                    if (sources[j] > sources[j + 1]) {
                        if (isUsingBitCalculate) {
                            xorSwap(sources, j, j + 1);
                        } else {
                            additionSwap(sources, j, j + 1);
                        }
                    }
                }
            }
        }
    }

    public static void sort(int[] sources, boolean leftIsBiggest) {
        sort(sources, leftIsBiggest, false);
    }

    private static void additionSwap(int[] sources, int leftPosition, int rightPosition) {
        sources[leftPosition] = sources[leftPosition] + sources[rightPosition];
        sources[rightPosition] = sources[leftPosition] - sources[rightPosition];
        sources[leftPosition] = sources[leftPosition] - sources[rightPosition];
    }

    private static void xorSwap(int[] sources, int leftPosition, int rightPosition) {
        sources[leftPosition] = sources[leftPosition] ^ sources[rightPosition];
        sources[rightPosition] = sources[leftPosition] ^ sources[rightPosition];
        sources[leftPosition] = sources[leftPosition] ^ sources[rightPosition];
    }
}
