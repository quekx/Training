package com.qkx.example.lc.hard;

/**
 * @author kaixin
 * @since 2020-11-30 21:22
 */
//Given an unsorted integer array nums, find the smallest missing positive integ
//er.
//
// Follow up: Could you implement an algorithm that runs in O(n) time and uses c
//onstant extra space.?
//
//
// Example 1:
// Input: nums = [1,2,0]
//Output: 3
// Example 2:
// Input: nums = [3,4,-1,1]
//Output: 2
// Example 3:
// Input: nums = [7,8,9,11,12]
//Output: 1
//
//
// Constraints:
//
//
// 0 <= nums.length <= 300
// -231 <= nums[i] <= 231 - 1
//
// Related Topics Array
// 👍 4700 👎 899


//leetcode submit region begin(Prohibit modification and deletion)
public class No41 {

    public static void main(String[] args) {
        int[] nums = {3, 4, -1, 1};
        System.out.println(new No41().firstMissingPositive(nums));
    }

    /**
     * 找到大于等于1的最小数x
     * 如果x不为1，则1为结果
     * 遍历数组，对于大于等于1的数num[i]
     * 将其交换摆放到num[num[i] - 1] <-> num[i]
     * 即1摆放到num[0], 2摆放到num[2] ..., num[i]摆放到num[num[i] - 1]
     * 这样交换结果为1, 2, 3 ...
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 1) {
                min = Math.min(min, nums[i]);
            }
        }

        if (min > 1) {
            return 1;
        }

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (num - 1 >= 0 && num - 1 <= nums.length - 1 && nums[num - 1] != num) {
                // 交换 i 和 p = num - 1
                int p = nums[i] - 1;
                int temp = nums[i];
                nums[i] = nums[p];
                nums[p] = temp;
                // 交换完成后继续比较
                num = nums[i];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

