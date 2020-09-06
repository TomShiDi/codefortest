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
    public static void sort(int[] sources, boolean leftIsBiggest) {
        if (leftIsBiggest) {
            for (int i = 1; i < sources.length; i++) {
                for (int j = i - 1; j >= 0; j--) {

                    if (sources[i] > sources[j]) {
                        if (j == 0) {
                            int temp = sources[0];
                            sources[0] = sources[i];
//                            for (int k = i; k > j + 1; k--) {
//                                sources[k] = sources[k - 1];
//                            }
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
//                            for (int k = i; k > j + 1; k--) {
//                                sources[k] = sources[k - 1];
//                            }
                            System.arraycopy(sources, j + 1, sources, j + 2, i - (j + 2) + 1);
                            sources[j + 2] = temp;
                        } else {
                        }
                        break;
                    }
                }
            }
        }

    }


    private static void insert(int[] sources, int leftPosition, int rightPosition) {
        sources[leftPosition] = sources[leftPosition] + sources[rightPosition];
        sources[rightPosition] = sources[leftPosition] - sources[rightPosition];
        sources[leftPosition] = sources[leftPosition] - sources[rightPosition];
    }

}
