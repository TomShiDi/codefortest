package com.demo.util;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @Author TomShiDi
 * @Since 2020/9/2
 * @Version 1.0
 */
public class InsertionSortMethod {


    /**
     * 找到插入位置后，数据整体后移
     *
     * @param sources       源数组
     * @param leftIsBiggest 当值为true时,为从大到小排序;当值为false时,为从小到大排列
     */
    public static void sort(int[] sources, boolean leftIsBiggest) {
        for (int i = 1; i < sources.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (leftIsBiggest) {
                    if (sources[i] > sources[j]) {
                        if (j == 0) {
                            int temp = sources[0];
                            sources[0] = sources[i];
                            System.arraycopy(sources, j + 1, sources, j + 2, i - (j + 2) + 1);
                            //sources[j+1]
                            sources[1] = temp;
                            break;
                        }
                    }
                    if (sources[i] < sources[j]) {
                        if (i - j > 1) {
                            int temp = sources[j + 1];
                            sources[j + 1] = sources[i];
                            System.arraycopy(sources, j + 1, sources, j + 2, i - (j + 2) + 1);
                            sources[j + 2] = temp;
                        }
                        break;
                    }
                } else {
                    if (sources[i] < sources[j]) {
                        if (j == 0) {
                            int temp = sources[0];
                            sources[0] = sources[i];
                            System.arraycopy(sources, j + 1, sources, j + 2, i - (j + 2) + 1);
                            //sources[j+1]
                            sources[1] = temp;
                            break;
                        }
                    }
                    if (sources[i] > sources[j]) {
                        if (i - j > 1) {
                            int temp = sources[j + 1];
                            sources[j + 1] = sources[i];
                            System.arraycopy(sources, j + 1, sources, j + 2, i - (j + 2) + 1);
                            sources[j + 2] = temp;
                        }
                        break;
                    }
                }
            }
        }

    }

    /**
     * 比较一位，数据后移一位
     *
     * @param sources       源数组
     * @param leftIsBiggest 当值为true时,为从大到小排序;当值为false时,为从小到大排列
     */
    public static void sort2(int[] sources, boolean leftIsBiggest) {
        for (int i = 1; i < sources.length; i++) {
            int temp = sources[i];
            int j = i;
            for (; j > 0; j--) {
                if (leftIsBiggest) {
                    if (temp > sources[j - 1]) {
                        sources[j] = sources[j - 1];
                    } else {
                        break;
                    }
                } else {
                    if (temp < sources[j - 1]) {
                        sources[j] = sources[j - 1];
                    } else {
                        break;
                    }
                }
            }
            sources[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arrays = {5, 3, 8, 6, 2, 7, 1, 2};
        sort(arrays, true);
        System.out.println(Arrays.toString(arrays));
    }

}
