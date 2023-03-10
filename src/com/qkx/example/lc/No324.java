package com.qkx.example.lc;

//Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2]
//< nums[3]....
//
// You may assume the input array always has a valid answer.
//
//
// Example 1:
//
//
//Input: nums = [1,5,1,1,6,4]
//Output: [1,6,1,5,1,4]
//Explanation: [1,4,1,5,1,6] is also accepted.
//
//
// Example 2:
//
//
//Input: nums = [1,3,2,2,3,1]
//Output: [2,3,1,3,1,2]
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 5 * 10⁴
// 0 <= nums[i] <= 5000
// It is guaranteed that there will be an answer for the given input nums.
//
//
//
//Follow Up: Can you do it in
//O(n) time and/or
//in-place with
//O(1) extra space?
//
// Related Topics Array Divide and Conquer Sorting Quickselect 👍 2640 👎 894


import com.qkx.example.utils.ArrayUtil;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class No324 {
    public void wiggleSort(int[] nums) {
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int x = 0, y = (nums.length + 1) >> 1;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                nums[i] = arr[x++];
            } else {
                nums[i] = arr[y++];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2,1,2,2,1};
        new No324().wiggleSort(nums);
        ArrayUtil.print(nums);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
