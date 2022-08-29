package com.demo.leetcode;

/**
 * 最长子串查找
 *
 * @author TomShiDi
 * @description 最长子串查找
 * @date 2022/4/3 13:56
 **/
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();
        System.out.println(lengthOfLongestSubstring.lengthOfLongestSubstring("aab"));
    }

    public int lengthOfLongestSubstring(String s) {
        int startIndex = 0;
        String subStr;
        int lengthMax = 0;
        int t;
        if (s.length() == 1) {
            return 1;
        }
        for (int i = 1; i < s.length(); i++) {
            subStr = s.substring(startIndex, i);
            if ((t = subStr.indexOf(s.charAt(i))) != -1) {
                if (subStr.length() > lengthMax) {
                    lengthMax = subStr.length();
                }
                startIndex = startIndex + t + 1;
            } else {
                if ((t = i - startIndex + 1) > lengthMax) {
                    lengthMax = t;
                }
            }
        }
        return lengthMax;
    }
}
