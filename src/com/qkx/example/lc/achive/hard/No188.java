package com.qkx.example.lc.achive.hard;

//You are given an integer array prices where prices[i] is the price of a given
//stock on the ith day.
//
// Design an algorithm to find the maximum profit. You may complete at most k tr
//ansactions.
//
// Notice that you may not engage in multiple transactions simultaneously (i.e.,
// you must sell the stock before you buy again).
//
//
// Example 1:
//
//
//Input: k = 2, prices = [2,4,1]
//Output: 2
//Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit =
//4-2 = 2.
//
//
// Example 2:
//
//
//Input: k = 2, prices = [3,2,6,5,0,3]
//Output: 7
//Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit =
//6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3
//-0 = 3.
//
//
//
// Constraints:
//
//
// 0 <= k <= 109
// 0 <= prices.length <= 1000
// 0 <= prices[i] <= 1000
//
// Related Topics Dynamic Programming
// 👍 2078 👎 132


//leetcode submit region begin(Prohibit modification and deletion)
public class No188 {
    /**
     * 解答失败: 测试用例:2 [3,3,5,0,0,3,1,4] 测试结果:8 期望结果:6 stdout:
     * @param args
     */
    public static void main(String[] args) {
        int k = 2;
        int[] prices = {3,3,5,0,0,3,1,4};
        System.out.println(new No188().maxProfit(k, prices));
    }

    /**
     * dp[i][k] 标识从i日到最后一日，操作k次的最大盈利
     * 1. 不在第i日购买
     * dp[i][k] = dp[i + 1][k]
     * 2. 在第i日购买, 遍历j日卖出 i + 1 <= j <= len - 1
     * dp[i][k] = max( dp[j + 1][k - ] + (price[j] - price[i]) )
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        if (k <= 0) {
            return 0;
        }

        int len = prices.length;
        int[][] dp = new int[k + 1][len + 1];
        // dp[i][k]
        for (int ki = 1; ki <= k; ki++) {
            for (int i = len - 2; i >= 0; i--) {
                int max = 0;
                for (int j = i + 1; j <= len - 1; j++) {
                    int earn = prices[j] - prices[i];
                    max = Math.max(max, dp[ki - 1][j + 1] + earn);
                }
                dp[ki][i] = Math.max(dp[ki][i + 1], max);
            }
        }

        return dp[k][0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

