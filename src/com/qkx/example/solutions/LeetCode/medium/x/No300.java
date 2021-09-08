package com.qkx.example.solutions.LeetCode.medium.x;

/**
 * Created by qkx on 16/11/17.
 */
public class No300 {

    /**
     * dp[i] 代表以 num[i] 为结尾的递增子序列最大长度
     * 如果 num[i] > num[k]
     * 以 num[k] 为结尾的最长递增子序列 + num[i] 也为递增子序列
     * <p>
     * dp[i] = max(
     * num[i] > num[k] ? dp[k] + 1 : 1
     * )
     *
     * @param nums
     * @return
     */
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
}
