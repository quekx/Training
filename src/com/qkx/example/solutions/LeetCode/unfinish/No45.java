package com.qkx.example.solutions.LeetCode.unfinish;

/**
 * @author kaixin
 * @since 2020-12-01 20:50
 */
//Given an array of non-negative integers nums, you are initially positioned at
//the first index of the array.
//
// Each element in the array represents your maximum jump length at that positio
//n.
//
// Your goal is to reach the last index in the minimum number of jumps.
//
// You can assume that you can always reach the last index.
//
//
// Example 1:
//
//
//Input: nums = [2,3,1,1,4]
//Output: 2
//Explanation: The minimum number of jumps to reach the last index is 2. Jump 1
//step from index 0 to 1, then 3 steps to the last index.
//
//
// Example 2:
//
//
//Input: nums = [2,3,0,1,4]
//Output: 2
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 3 * 104
// 0 <= nums[i] <= 105
//
// Related Topics Array Greedy
// 👍 3328 👎 161


//leetcode submit region begin(Prohibit modification and deletion)
public class No45 {
    public static void main(String[] args) {
        int[] nums = {2,3,0,1,4};
        System.out.println(new No45().jump(nums));
    }

    public int jump(int[] nums) {
        int len = nums.length;
        int[] step = new int[len];
        step[0] = 0;
        for (int i = 0; i <= len - 2; i++) {
            for (int k = 1; k <= nums[i] && i + k < len; k++) {
                int end = i + k;
                if (step[end] == 0) {
                    step[end] = step[i] + 1;
                } else {
                    step[end] = Math.min(step[end], step[i] + 1);
                }
            }
        }
//        System.out.println(Arrays.toString(step));
        return step[len - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

