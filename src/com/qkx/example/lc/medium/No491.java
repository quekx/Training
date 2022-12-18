package com.qkx.example.lc.medium;

import java.util.*;

/**
 * @author kaixin
 * @since 2022-02-22 11:45
 */
//Given an integer array nums, return all the different possible increasing
//subsequences of the given array with at least two elements. You may return the
//answer in any order.
//
// The given array may contain duplicates, and two equal integers should also
//be considered a special case of increasing sequence.
//
//
// Example 1:
//
//
//Input: nums = [4,6,7,7]
//Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
//
//
// Example 2:
//
//
//Input: nums = [4,4,3,2,1]
//Output: [[4,4]]
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 15
// -100 <= nums[i] <= 100
//
// Related Topics Array Hash Table Backtracking Bit Manipulation 👍 1367 👎 146


//leetcode submit region begin(Prohibit modification and deletion)
class No491 {
    /**
     *  解答成功:
     * 	执行耗时:5 ms,击败了98.29% 的Java用户
     * 	内存消耗:48.1 MB,击败了96.27% 的Java用户
     *
     * 	解答成功:
     * 	执行耗时:3 ms,击败了100.00% 的Java用户
     * 	内存消耗:47.9 MB,击败了97.20% 的Java用户
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<Integer> tmp = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, 0, Integer.MIN_VALUE, tmp, ans);
        return ans;
    }

    private void dfs(int[] nums, int i, int last, List<Integer> tmp, List<List<Integer>> ans) {
        if (nums.length == i) {
            if (tmp.size() >= 2) {
                ans.add(new ArrayList<>(tmp));
            }
            return;
        }
        int cur = nums[i];
        if (cur >= last) {
            tmp.add(cur);
            dfs(nums, i + 1, cur, tmp, ans);
            tmp.remove(tmp.size() - 1);
        }
        if (cur != last) {
            dfs(nums, i + 1, last, tmp, ans);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2};
        System.out.println(new No491().findSubsequences(nums));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
