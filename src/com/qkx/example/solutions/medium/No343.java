package com.qkx.example.solutions.medium;

/**
 * Created by qkx on 16/11/24.
 */
public class No343 {
    public int integerBreak(int n) {
        if (n == 2) return 1;

        int[] dp = new int[n + 1];
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            int a, b, temp = 0;
            for (int k = 1; k <= i / 2; k++) {
                a = Math.max(k, dp[k]);
                b = Math.max(i - k, dp[i - k]);
                temp = Math.max(temp, a * b);
            }
            dp[i] = temp;
        }

        return dp[n];
    }
}
