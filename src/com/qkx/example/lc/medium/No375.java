package com.qkx.example.lc.medium;

/**
 * Created by qkx on 17/7/5.
 */
public class No375 {
    /**
     * We are playing the Guess Game. The game is as follows:
     * I pick a number from 1 to n. You have to guess which number I picked.
     * Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
     * However, when you guess a particular number x, and you guess wrong, you pay $x.
     * You win the game when you guess the number I picked.
     *
     * n = 10, I pick 8.
     * First round:  You guess 5, I tell you that it's higher. You pay $5.
     * Second round: You guess 7, I tell you that it's higher. You pay $7.
     * Third round:  You guess 9, I tell you that it's lower. You pay $9.
     * Game over. 8 is the number I picked.
     * You end up paying $5 + $7 + $9 = $21.
     */

    public static int getMoneyAmount(int n) {
        if (n <= 0) return 0;

        int[][] dp = new int[n + 1][n + 1];

        for (int delta = 1; delta <= n - 1; delta++) {
            for (int left = 1; left <= n - delta; left++) {
                // [left, left + delta]
                int right = left + delta;
                int min = Math.min(dp[left + 1][right] + left, dp[left][right - 1] + left + delta);
                for (int i = left + 1; i <= right - 1; i++) {
                    int cost = i + Math.max(dp[left][i - 1], dp[i + 1][right]);
                    min = Math.min(min, cost);
                }
                dp[left][left + delta] = min;
            }
        }

        return dp[1][n];
    }
}
