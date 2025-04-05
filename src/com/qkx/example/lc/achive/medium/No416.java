package com.qkx.example.lc.achive.medium;

import java.util.Arrays;

/**
 * @author kaixin
 * @since 2021-09-17 15:52
 */
//Given a non-empty array nums containing only positive integers, find if the
//array can be partitioned into two subsets such that the sum of elements in both
//subsets is equal.
//
//
// Example 1:
//
//
//Input: nums = [1,5,11,5]
//Output: true
//Explanation: The array can be partitioned as [1, 5, 5] and [11].
//
//
// Example 2:
//
//
//Input: nums = [1,2,3,5]
//Output: false
//Explanation: The array cannot be partitioned into equal sum subsets.
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 200
// 1 <= nums[i] <= 100
//
// Related Topics Array Dynamic Programming 👍 5375 👎 97


//leetcode submit region begin(Prohibit modification and deletion)
public class No416 {
    /**
     * dp[i][k] 0 <= i <= n-1, 0 <= k <= target
     * 代表 s[0][i] 中，是否存在子集合的和为k
     * dp[i][k] = nums[i] == k ? true : ((dp[i - 1][k - nums[i]) || dp[i - 1][k])
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }

        Arrays.sort(nums);
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) != 0) {
            return false;
        }

        int target = sum >> 1;
        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        for (int i = 1; i <= nums.length; i++) {
            for (int k = 0; k <= target; k++) {
                if (nums[i - 1] == k) {
                    dp[i][k] = true;
                } else if (nums[i - 1] < k) {
                    dp[i][k] = dp[i - 1][k] || dp[i - 1][k - nums[i - 1]];
                } else {
                    dp[i][k] = dp[i - 1][k];
                }
            }
        }
        return dp[nums.length - 1][target];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

