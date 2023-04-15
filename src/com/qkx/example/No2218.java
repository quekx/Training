package com.qkx.example;

import java.util.List;

/**
 * 解答成功:
 * 	执行耗时:210 ms,击败了56.79% 的Java用户
 * 	内存消耗:45.3 MB,击败了54.94% 的Java用户
 */
public class No2218 {
    // dp[i][j] 表示第 i - 1 堆，拿一共拿 j 个的最大值
    // dp[i][j] = sum[p[i - 1][0...x-1]] + dp[i - 1][j - x - 1];  1 <= x <= j
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int n = piles.size();
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            List<Integer> pile = piles.get(i - 1);
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j];
                int score = 0;
                for (int x = 1; x <= pile.size() && x <= j; x++) {
                    score += pile.get(x - 1);
                    dp[i][j] = Math.max(dp[i][j], score + dp[i - 1][j - x]);
                }
            }
        }
        return dp[n][k];
    }
}

//There are n piles of coins on a table. Each pile consists of a positive
//number of coins of assorted denominations.
//
// In one move, you can choose any coin on top of any pile, remove it, and add
//it to your wallet.
//
// Given a list piles, where piles[i] is a list of integers denoting the
//composition of the iᵗʰ pile from top to bottom, and a positive integer k, return the
//maximum total value of coins you can have in your wallet if you choose exactly k
//coins optimally.
//
//
// Example 1:
//
//
//Input: piles = [[1,100,3],[7,8,9]], k = 2
//Output: 101
//Explanation:
//The above diagram shows the different ways we can choose k coins.
//The maximum total we can obtain is 101.
//
//
// Example 2:
//
//
//Input: piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
//Output: 706
//Explanation:
//The maximum total can be obtained if we choose all coins from the last pile.
//
//
//
// Constraints:
//
//
// n == piles.length
// 1 <= n <= 1000
// 1 <= piles[i][j] <= 10⁵
// 1 <= k <= sum(piles[i].length) <= 2000
//
//
// Related Topics Array Dynamic Programming Prefix Sum 👍 1650 👎 26
