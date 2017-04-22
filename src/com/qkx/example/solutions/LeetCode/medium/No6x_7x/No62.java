package com.qkx.example.solutions.LeetCode.medium.No6x_7x;

/**
 * Created by qkx on 16/5/24.
 */
public class No62 {
    public static int uniquePaths(int m, int n) {


        m = m - 1;
        n = n - 1;

        int max = m > n ? m : n;
        int min = m < n ? m : n;

        long res = getFactorialProduct(max + 1, max + min) / getFactorialProduct(1, min);
        return (int) res;
    }
    private static long getFactorialProduct(int start, int end) {
        long res = 1;
        for (int i = start; i <= end; i++) {
            res = res * i;
        }
        return res;
    }
}
