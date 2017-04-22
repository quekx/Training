package com.qkx.example.solutions.LeetCode.medium.x;

/**
 * Created by qkx on 16/11/17.
 */
public class No264 {
    public int nthUglyNumber(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int twoIndex = 0;
        int threeIndex = 0;
        int fiveIndex = 0;

        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = min(dp[twoIndex] * 2, dp[threeIndex] * 3, dp[fiveIndex] * 5);

            if (dp[i] == dp[twoIndex] * 2) {
                twoIndex++;
            }
            if (dp[i] == dp[threeIndex] * 3) {
                threeIndex++;
            }
            if (dp[i] == dp[fiveIndex] * 5) {
                fiveIndex++;
            }
        }
        return dp[n - 1];
    }

    private int min(int a, int b, int c) {
        int temp = Math.min(a, b);
        return Math.min(temp, c);
    }
}
