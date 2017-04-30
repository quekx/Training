package com.qkx.example.solutions.LeetCode.easy;

import java.util.Arrays;

/**
 * Created by qkx on 17/4/30.
 */
public class No242 {
    public static boolean isAnagram(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;

        return calculate(s) == calculate(t);
    }

    private static int calculate(String str) {
        int[] preNums = getPreNums(26);
        int result = 1;
        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i) - 'a';
            result *= preNums[index];
        }
        return result;
    }

    private static int[] getPreNums(int n) {
        int[] result = new int[n];
        int count = 1;
        result[0] = 2;
        int x = 3;
        while (count < n) {
            for (int num : result) {
                if (x % num == 0) {
                    x++;
                    break;
                }

                if (num * num > x || num == 0) {
                    result[count++] = x++;
                    break;
                }
            }
        }
        return result;
    }
}
