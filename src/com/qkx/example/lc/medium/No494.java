package com.qkx.example.lc.medium;

/**
 * @author kaixin
 * @since 2022-02-22 14:50
 */
//You are given an integer array nums and an integer target.
//
// You want to build an expression out of nums by adding one of the symbols '+'
//and '-' before each integer in nums and then concatenate all the integers.
//
//
// For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1
//and concatenate them to build the expression "+2-1".
//
//
// Return the number of different expressions that you can build, which
//evaluates to target.
//
//
// Example 1:
//
//
//Input: nums = [1,1,1,1,1], target = 3
//Output: 5
//Explanation: There are 5 ways to assign symbols to make the sum of nums be
//target 3.
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
//
//
// Example 2:
//
//
//Input: nums = [1], target = 1
//Output: 1
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 20
// 0 <= nums[i] <= 1000
// 0 <= sum(nums[i]) <= 1000
// -1000 <= target <= 1000
//
// Related Topics Array Dynamic Programming Backtracking 👍 6219 👎 241


//leetcode submit region begin(Prohibit modification and deletion)
class No494 {

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        int target = 3;
        System.out.println(new No494().findTargetSumWays(nums, target));
    }

    private int result = 0;

    /**
     * 解答成功:
     * 执行耗时:349 ms,击败了41.04% 的Java用户
     * 内存消耗:42.1 MB,击败了45.20% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        traverse(nums, target, 0);
        return result;
    }

    private void traverse(int[] nums, int target, int i) {
        if (nums.length - 1 == i) {
            if (target == nums[i]) {
                result++;
            }
            if (target == -nums[i]) {
                result++;
            }
            return;
        }

        traverse(nums, target - nums[i], i + 1);
        traverse(nums, target + nums[i], i + 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
