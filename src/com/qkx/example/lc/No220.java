package com.qkx.example.lc;

import java.util.*;

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
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        int n = nums.length;
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 6, 2};
        int indexDiff = 1;
        int valueDiff = 2;
        System.out.println(new No220().containsNearbyAlmostDuplicate(nums, indexDiff, valueDiff));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

