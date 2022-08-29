package com.demo.algorithm;

import java.util.Scanner;

/**
 * 判断一个数是否为2的幂
 * @author TomShiDi
 * @description
 * @date 2021/7/27 20:08
 **/
public class StartUp {
    public static void main(String[] args) {
        int N = 5;
        String pattern = "1";
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        while (N-- > 0) {
            int input = scanner.nextInt();
            count = 0;
            String binaryString = Integer.toBinaryString(input);
            if (input > 0) {
                int index = 0;
                while ((index = binaryString.indexOf(pattern, index)) != -1) {
                    index = index + pattern.length();
                    count++;
                }
                if (count == 1) {
                    System.out.println("是2的幂： " + input);
                } else {
                    System.out.printf("NOT： %s  count： %s%n", input, count);
                }
            } else {
                System.out.printf("NOT： %s  \n", input);
            }
        }
    }
}
