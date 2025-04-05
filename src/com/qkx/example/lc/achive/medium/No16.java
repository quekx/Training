package com.qkx.example.lc.achive.medium;

import java.util.Arrays;

/**
 * @author kaixin
 * @since 2021-01-21 15:51
 */
//Given an array nums of n integers and an integer target, find three integers i
//n nums such that the sum is closest to target. Return the sum of the three integ
//ers. You may assume that each input would have exactly one solution.
//
//
// Example 1:
//
//
//Input: nums = [-1,2,1,-4], target = 1
//Output: 2
//Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
//
//
//
// Constraints:
//
//
// 3 <= nums.length <= 10^3
// -10^3 <= nums[i] <= 10^3
// -10^4 <= target <= 10^4
//
// Related Topics Array Two Pointers
// 👍 2805 👎 161


//leetcode submit region begin(Prohibit modification and deletion)
public class No16 {
    public static void main(String[] args) {
        int[] nums = {-1,2,1,-4};
        int target = 1;
        System.out.println(new No16().threeSumClosest(nums, target));
    }

    /**
     *
     * //runtime:4ms
     * //memory:38.9 MB
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length <= 2) {
            return 0;
        }

        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        int i = 0;
        while (i <= nums.length - 3) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return target;
                } else if (sum < target) {
                    res = Math.abs(target - sum) < Math.abs(target - res) ? sum : res;
                    left++;
                } else {
                    res = Math.abs(target - sum) < Math.abs(target - res) ? sum : res;
                    right--;
                }
            }
            i++;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
