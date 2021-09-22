package com.qkx.example;

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

        return find(0, nums, 0, target);
    }

    private boolean find(int preSum, int[] nums, int i, int target) {
        if (i >= nums.length) {
            return false;
        }

        int sum = preSum + nums[i];
        if (sum == target) {
            return true;
        } else if (sum > target) {
            return false;
        }

        if (find(preSum, nums, i + 1, target)) {
            return true;
        }
        if (find(sum, nums, i + 1, target)) {
            return true;
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

