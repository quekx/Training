package com.qkx.example.lc.achive.medium;

/**
 * @author kaixin
 * @since 2021-11-18 14:45
 */
//Given an integer array nums of size n, return the minimum number of moves
//required to make all array elements equal.
//
// In one move, you can increment n - 1 elements of the array by 1.
//
//
// Example 1:
//
//
//Input: nums = [1,2,3]
//Output: 3
//Explanation: Only three moves are needed (remember each move increments two
//elements):
//[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
//
//
// Example 2:
//
//
//Input: nums = [1,1,1]
//Output: 0
//
//
//
// Constraints:
//
//
// n == nums.length
// 1 <= nums.length <= 10⁵
// -10⁹ <= nums[i] <= 10⁹
// The answer is guaranteed to fit in a 32-bit integer.
//
// Related Topics Array Math 👍 1170 👎 1427


//leetcode submit region begin(Prohibit modification and deletion)
public class No453 {
    /**
     * 每操作一次n-1个数加一，相当于其中一个数减一
     * 看做将所有数据减到最小值即可
     *
     * 解答成功:
     * 执行耗时:1 ms,击败了100.00% 的Java用户
     * 内存消耗:39.3 MB,击败了85.34% 的Java用户
     */
    public int minMoves(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int min = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
        }
        int count = 0;
        for (int num : nums) {
            count += num - min;
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

