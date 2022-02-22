package com.qkx.example.lc.medium;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author kaixin
 * @since 2022-02-22 17:55
 */
//Given a circular integer array nums (i.e., the next element of nums[nums.
//length - 1] is nums[0]), return the next greater number for every element in nums.
//
// The next greater number of a number x is the first greater number to its
//traversing-order next in the array, which means you could search circularly to find
//its next greater number. If it doesn't exist, return -1 for this number.
//
//
// Example 1:
//
//
//Input: nums = [1,2,1]
//Output: [2,-1,2]
//Explanation: The first 1's next greater number is 2;
//The number 2 can't find next greater number.
//The second 1's next greater number needs to search circularly, which is also 2
//.
//
//
// Example 2:
//
//
//Input: nums = [1,2,3,4,3]
//Output: [2,3,4,-1,4]
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 10⁴
// -10⁹ <= nums[i] <= 10⁹
//
// Related Topics Array Stack Monotonic Stack 👍 4081 👎 122


//leetcode submit region begin(Prohibit modification and deletion)
class No503 {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,3};
        System.out.println(Arrays.toString(new No503().nextGreaterElements(nums)));
    }

    /**
     * 20:32	info
     * 解答成功:
     * 执行耗时:25 ms,击败了49.67% 的Java用户
     * 内存消耗:54.7 MB,击败了27.88% 的Java用户
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                Integer pre = stack.pop();
                result[pre] = nums[i];
            }
            stack.push(i);
        }
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                Integer pre = stack.pop();
                result[pre] = nums[i];
            }
        }
        while (!stack.isEmpty()) {
            Integer i = stack.pop();
            result[i] = -1;
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

