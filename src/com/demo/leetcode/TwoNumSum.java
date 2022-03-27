package com.demo.leetcode;

import java.util.*;

/**
 * @author TomShiDi
 * @description
 * @date 2022/3/26 13:30
 **/
public class TwoNumSum {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);
        System.out.println(Arrays.toString(result));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length - 1);
        map.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            int another = target - nums[i];
            if (nums[i + -1] == another) {
                return new int[]{i - 1, i};
            }
            if (i < nums.length - 1) {
                if (nums[i + 1] == another) {
                    return new int[]{i, i + 1};
                }
            }
            if (map.containsKey(another)) {
                return new int[]{map.get(another), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
