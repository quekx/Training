package com.qkx.example.solutions.LeetCode;

/**
 * @author kaixin
 * @since 2020-12-15 15:25
 */
//Given an array of integers, find out whether there are two distinct indices i
//and j in the array such that the absolute difference between nums[i] and nums[j]
// is at most t and the absolute difference between i and j is at most k.
//
//
// Example 1:
// Input: nums = [1,2,3,1], k = 3, t = 0
//Output: true
// Example 2:
// Input: nums = [1,0,1,1], k = 1, t = 2
//Output: true
// Example 3:
// Input: nums = [1,5,9,1,5,9], k = 2, t = 3
//Output: false
//
//
// Constraints:
//
//
// 0 <= nums.length <= 2 * 104
// -231 <= nums[i] <= 231 - 1
// 0 <= k <= 104
// 0 <= t <= 231 - 1
//
// Related Topics Sort Ordered Map
// 👍 1429 👎 1517


//leetcode submit region begin(Prohibit modification and deletion)
public class No220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= k && i + j < nums.length; j++) {
                long a = nums[i];
                long b = nums[i + j];
                if (Math.abs(a - b) <= t) {
                    return true;
                }
            }
        }

        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

