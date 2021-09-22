package com.qkx.example.solutions.LeetCode.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author kaixin
 * @since 2021-09-22 15:34
 */
//Given an integer array nums of length n where all the integers of nums are in
//the range [1, n] and each integer appears once or twice, return an array of all
//the integers that appears twice.
//
// You must write an algorithm that runs in O(n) time and uses only constant
//extra space.
//
//
// Example 1:
// Input: nums = [4,3,2,7,8,2,3,1]
//Output: [2,3]
// Example 2:
// Input: nums = [1,1,2]
//Output: [1]
// Example 3:
// Input: nums = [1]
//Output: []
//
//
// Constraints:
//
//
// n == nums.length
// 1 <= n <= 10⁵
// 1 <= nums[i] <= n
// Each element in nums appears once or twice.
//
// Related Topics Array Hash Table 👍 4263 👎 198


//leetcode submit region begin(Prohibit modification and deletion)
public class No442 {

    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(new No442().findDuplicates(nums));
    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new LinkedList<>();
        int curIndex = 0;
        while (curIndex < nums.length) {
            int cur = nums[curIndex];
            System.out.println("curIndex >> " + curIndex + ", cur >> " + cur + ", nums >> " + Arrays.toString(nums) + ", res >> " + list);
            if (cur <= 0) {
                curIndex++;
                continue;
            }
            int exchangeIndex = cur - 1;
            int exchange = nums[exchangeIndex];
            if (exchange == 0) {
                list.add(cur);
                nums[curIndex] = -1;
                curIndex++;
            } else if (curIndex == exchangeIndex) {
                nums[exchangeIndex] = 0;
                curIndex++;
            } else {
                nums[exchangeIndex] = 0;
                nums[curIndex] = exchange;
            }
        }
        return list;
    }
    /**
     * num[cur - 1] 置为0
     * 然后把原来的num[cur - 1]和num[curIndex]替换
     */
}
//leetcode submit region end(Prohibit modification and deletion)
