package com.qkx.example.solutions.LeetCode.easy;

/**
 * Created by qkx on 17/6/23.
 */
public class No344 {
    public String reverseString(String s) {
        if (s == null || s.length() <= 1) return s;

        int len = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < len / 2; i++) {
            char c = chars[i];
            chars[i] = chars[len - 1 - i];
            chars[len - 1 - i] = c;
        }
        return new String(chars);
    }
}
