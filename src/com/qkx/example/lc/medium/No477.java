package com.qkx.example.lc.medium;

/**
 * @author kaixin
 * @since 2022-02-21 16:41
 */
//The Hamming distance between two integers is the number of positions at which
//the corresponding bits are different.
//
// Given an integer array nums, return the sum of Hamming distances between all
//the pairs of the integers in nums.
//
//
// Example 1:
//
//
//Input: nums = [4,14,2]
//Output: 6
//Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 001
//0 (just
//showing the four bits relevant in this case).
//The answer will be:
//HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 +
//2 + 2 = 6.
//
//
// Example 2:
//
//
//Input: nums = [4,14,4]
//Output: 4
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 10⁴
// 0 <= nums[i] <= 10⁹
// The answer for the given input will fit in a 32-bit integer.
//
// Related Topics Array Math Bit Manipulation 👍 1549 👎 75


//leetcode submit region begin(Prohibit modification and deletion)
class No477 {
    public int totalHammingDistance(int[] nums) {
        int total = 0;
        for (int mask = 0; mask < 32; mask++) {
            int zero = 0;
            int one = 0;
            for (int num : nums) {
                if ((num & (1 << mask)) == 0) {
                    zero++;
                } else {
                    one++;
                }
            }
            total += zero * one;
        }
        return total;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

