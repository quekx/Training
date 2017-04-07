package com.qkx.example.solutions.medium.x;

/**
 * Created by qkx on 16/11/17.
 */
public class No300 {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        int res = 1;

        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i <= n - 1; i++) {
            int temp = 1;
            for (int k = i - 1; k >= 0; k--) {
                if (nums[i] > nums[k]) {
                    temp = Math.max(temp, dp[k] + 1);
                }
            }
            dp[i] = temp;

            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int lengthOfLIS2(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return 1;


        return 0;
    }
}
