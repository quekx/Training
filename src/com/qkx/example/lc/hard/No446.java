package com.qkx.example.lc.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
     * dp(i, x) 记录以 num[i] 为结尾, 等差为 x, 长度 >=3 子序列数目
     * f[i, x] 记录以 num[i] 为结尾，等差为 x, 长度 = 2 子序列数目
     * <p>
     * dp(i, x) = sum(dp[k, x] + f[k, x])
     * if (nums[i] - nums[k] == x)
     * <p>
     * 解答成功: 执行耗时:405 ms,击败了21.97% 的Java用户 内存消耗:180.2 MB,击败了7.15% 的Java用户
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        Map<Long, Integer>[] f = new HashMap[n];
        Map<Long, Integer>[] dp = new HashMap[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
            f[i] = new HashMap<>();
            for (int k = 0; k < i; k++) {
                long x = (long) nums[i] - nums[k];
                int count = dp[k].getOrDefault(x, 0) + f[k].getOrDefault(x, 0);
                dp[i].put(x, dp[i].getOrDefault(x, 0) + count);
                // 计算二元祖
                f[i].put(x, f[i].getOrDefault(x, 0) + 1);
            }
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(f));
        int ans = 0;
        for (Map<Long, Integer> map : dp) {
            for (int count : map.values()) {
                ans += count;
            }
        }
        return ans;
    }

    /**
     * 解答成功: 执行耗时:207 ms,击败了79.35% 的Java用户 内存消耗:93.7 MB,击败了78.70% 的Java用户
     */
    public int numberOfArithmeticSlices2(int[] nums) {
        int n = nums.length;
        Map<Long, Integer>[] dp = new HashMap[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
            for (int k = 0; k < i; k++) {
                long x = (long) nums[i] - nums[k];
                int count = dp[k].getOrDefault(x, 0);
                int origin = dp[i].getOrDefault(x, 0);
                dp[i].put(x, count + origin + 1);
                ans += count;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {0, 2000000000, -294967296};
        System.out.println(new No446().numberOfArithmeticSlices(nums));
        long x = -294967296L - 2000000000;
        System.out.println(x);
    }

}
//leetcode submit region end(Prohibit modification and deletion)

