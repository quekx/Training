package com.qkx.example.solutions.LeetCode.hard;

/**
 * @author kaixin
 * @since 2021-09-24 15:09
 */
//You are given n balloons, indexed from 0 to n - 1. Each balloon is painted
//with a number on it represented by an array nums. You are asked to burst all the
//balloons.
//
// If you burst the iᵗʰ balloon, you will get nums[i - 1] * nums[i] * nums[i + 1
//] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if
//there is a balloon with a 1 painted on it.
//
// Return the maximum coins you can collect by bursting the balloons wisely.
//
//
// Example 1:
//
//
//Input: nums = [3,1,5,8]
//Output: 167
//Explanation:
//nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
//coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
//
// Example 2:
//
//
//Input: nums = [1,5]
//Output: 10
//
//
//
// Constraints:
//
//
// n == nums.length
// 1 <= n <= 500
// 0 <= nums[i] <= 100
//
// Related Topics Array Dynamic Programming 👍 4245 👎 122


//leetcode submit region begin(Prohibit modification and deletion)
public class No312 {
    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8};
        System.out.println(new No312().maxCoins(nums));
    }

    /**
     * dp[i][j] (0 <= i <= j <= nums.length() - 1)
     * 代表num(i, j)打爆全部气球的最大分数值
     * dp[i][i] = nums[i - 1] * nums[i] * nums[i + 1]
     * <p>
     * 假设 num(i, j) 内最后打爆的气球为 k（i <= k <= j）
     * 说明 num[i, k - 1] + num[k + 1, j]已经被打爆
     * num[i, k - 1] 打爆的分数：dp[i, k - 1]
     * num[k + 1, j] 打爆的分数：dp[k + 1][j]
     * k 打爆的分数：nums[i - 1] * nums[k] * nums[j + 1]，因为 nums(i, j) 内都被打爆了
     * <p>
     * dp[i][j][k] = dp[i, k - 1] + dp[k + 1][j] + nums[i - 1] * nums[k] * nums[j + 1]
     * dp[i][j] = max(dp[i][j][k]）
     *
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i <= n - 1; i++) {
            int c1 = i - 1 < 0 ? 1 : nums[i - 1];
            int c2 = nums[i];
            int c3 = i + 1 > n - 1 ? 1 : nums[i + 1];
            dp[i][i] = c1 * c2 * c3;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - 1 && i + len - 1 <= n - 1; i++) {
                int j = i + len - 1;
                int max = Integer.MIN_VALUE;
                for (int k = i; k <= j; k++) {
                    int c1 = i - 1 < 0 ? 1 : nums[i - 1];
                    int c2 = nums[k];
                    int c3 = j + 1 > n - 1 ? 1 : nums[j + 1];
                    int res = c1 * c2 * c3;
                    if (k == i) {
                        max = Math.max(max, res + dp[i + 1][j]);
                    } else if (k == j) {
                        max = Math.max(max, res + dp[i][j - 1]);
                    } else {
                        max = Math.max(max, res + dp[k + 1][j] + dp[i][k - 1]);
                    }
                }
                dp[i][j] = max;
            }
        }
        return dp[0][n - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

