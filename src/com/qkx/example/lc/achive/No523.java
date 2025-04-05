package com.qkx.example.lc.achive;

//Given an integer array nums and an integer k, return true if nums has a good
//subarray or false otherwise.
//
// A good subarray is a subarray where:
//
//
// its length is at least two, and
// the sum of the elements of the subarray is a multiple of k.
//
//
// Note that:
//
//
// A subarray is a contiguous part of the array.
// An integer x is a multiple of k if there exists an integer n such that x = n
//* k. 0 is always a multiple of k.
//
//
//
// Example 1:
//
//
//Input: nums = [23,2,4,6,7], k = 6
//Output: true
//Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up
//to 6.
//
//
// Example 2:
//
//
//Input: nums = [23,2,6,4,7], k = 6
//Output: true
//Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose
//elements sum up to 42.
//42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
//
//
// Example 3:
//
//
//Input: nums = [23,2,6,4,7], k = 13
//Output: false
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 10⁵
// 0 <= nums[i] <= 10⁹
// 0 <= sum(nums[i]) <= 2³¹ - 1
// 1 <= k <= 2³¹ - 1
//
//
// Related Topics Array Hash Table Math Prefix Sum 👍 4587 👎 448


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class No523 {
    /**
     * 解答成功:
     * 	执行耗时:23 ms,击败了37.11% 的Java用户
     * 	内存消耗:62.1 MB,击败了13.15% 的Java用户
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Long, Integer> map = new HashMap<>();
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = (sum + nums[i]) % k;
            if (sum == 0 && i >= 1) {
                return true;
            }
            Integer preIdx = map.get(sum);
            if (preIdx == null) {
                map.put(sum, i);
            } else if (i > preIdx + 1) {
                return true;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

