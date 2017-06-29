package com.qkx.example.solutions.LeetCode.medium;

/**
 * Created by qkx on 17/6/29.
 */
public class No357 {
    public int countNumbersWithUniqueDigits(int n) {
        if (n < 0 || n > 10) return 0;
        if (n == 0) return 1;

        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += calculate(i);
        }
        return result;
    }

    private int calculate(int n) {
        if (n == 1) return 10;

        int result = 9;
        for (int i = 1; i <= n - 1; i++) {
            result *= (10 - i);
        }
        return result;
    }
}
