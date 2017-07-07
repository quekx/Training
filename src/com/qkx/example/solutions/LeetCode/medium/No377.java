package com.qkx.example.solutions.LeetCode.medium;

/**
 * Created by qkx on 17/7/7.
 */
public class No377 {
    /**
     * Given an integer array with all positive numbers and no duplicates,
     * find the number of possible combinations that add up to a positive integer target.
     * nums = [1, 2, 3]
     * target = 4
     *
     * The possible combination ways are:
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * (3, 1)
     */

    // 无重复
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;

        int len = nums.length;
        int[][] dp = new int[target + 1][len + 1];
        for (int i = 1; i <= len; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i <= target; i++) {
            for (int j = 1; j <= len; j++) {
                if (nums[j - 1] > i) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i - nums[j - 1]][j] + dp[i][j - 1];
                }
            }
        }

        return dp[target][len];
    }
}
