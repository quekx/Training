package com.qkx.example.solutions.LeetCode.medium.x;

/**
 * Created by qkx on 17/3/15.
 */
public class No518 {
    /**
     * You are given coins of different denominations and a total amount of money.
     * Write a function to compute the number of combinations that make up that amount.
     * You may assume that you have infinite number of each kind of coin.
     */

    public int change(int amount, int[] coins) {

        int coinNums = coins.length;
        int[][] dp = new int[amount + 1][coinNums + 1];
        for (int k = 0; k <= coinNums; k++) {
            dp[0][k] = 1;
        }

        for (int i = 1; i <= amount; i++) {
            for (int k = 1; k <= coinNums; k++) {
                // index = k - 1
                if (coins[k - 1] <= i) {
                    dp[i][k] = dp[i][k - 1] + dp[i - coins[k - 1]][k];
                } else {
                    dp[i][k] = dp[i][k - 1];
                }
            }
        }
        return dp[amount][coinNums];
    }
}
