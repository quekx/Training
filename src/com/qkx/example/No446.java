package com.qkx.example;

/**
 * @author kaixin
 * @since 2022-04-21 15:45
 */
//Given an integer array nums, return the number of all the arithmetic
//subsequences of nums.
//
// A sequence of numbers is called arithmetic if it consists of at least three
//elements and if the difference between any two consecutive elements is the same.
//
//
//
// For example, [1, 3, 5, 7, 9], [7, 7, 7, 7], and [3, -1, -5, -9] are
//arithmetic sequences.
// For example, [1, 1, 2, 5, 7] is not an arithmetic sequence.
//
//
// A subsequence of an array is a sequence that can be formed by removing some
//elements (possibly none) of the array.
//
//
// For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
//
//
// The test cases are generated so that the answer fits in 32-bit integer.
//
//
// Example 1:
//
//
//Input: nums = [2,4,6,8,10]
//Output: 7
//Explanation: All arithmetic subsequence slices are:
//[2,4,6]
//[4,6,8]
//[6,8,10]
//[2,4,6,8]
//[4,6,8,10]
//[2,4,6,8,10]
//[2,6,10]
//
//
// Example 2:
//
//
//Input: nums = [7,7,7,7,7]
//Output: 16
//Explanation: Any subsequence of this array is arithmetic.
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 1000
// -2³¹ <= nums[i] <= 2³¹ - 1
//
// Related Topics Array Dynamic Programming 👍 1344 👎 85


//leetcode submit region begin(Prohibit modification and deletion)
class No446 {

    /**
     * dp1(i, x) 记录以 num[i] 为结尾, 等差为 x 的子序列数目
     * dp2(i, x) 记录以 num[i] 为结尾, 前面 i 之前差为 x 并且构不成等差序列的元素个数
     *
     * dp1(i, x) =
     *   dp1(i - 1, x) + dp2(i - 1, x)
     *
     * dp2(i, x) =
     *   count(nums[x] - nums[i] = x && dp1(i - 1, x) = 0)
     *
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {

        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

