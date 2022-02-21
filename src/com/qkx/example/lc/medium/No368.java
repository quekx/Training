package com.qkx.example.lc.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by qkx on 17/7/2.
 */
public class No368 {
    /**
     * Given a set of distinct positive integers,
     * find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
     * If there are multiple solutions, return any subset is fine.
     *
     * nums: [1,2,3]
     * Result: [1,2] (of course, [1,3] will also be ok)
     *
     * nums: [1,2,4,8]
     * Result: [1,2,4,8]
     */

    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> result = new LinkedList<>();
        if (nums == null || nums.length == 0) return result;

        Arrays.sort(nums);

        int len = nums.length;
        int[] preIndex = new int[len];

        int largest = 1;
        int largestIndex = 0;

        int[] dp = new int[len];
        preIndex[0] = -1;
        dp[0] = 1;
        for (int i = 1; i <= len - 1; i++) {
            int pre = -1;
            int max = 1;
            for (int k = 0; k <= i - 1; k++) {
                if (nums[i] % nums[k] == 0 && dp[k] + 1 > max) {
                    max = dp[k] + 1;
                    pre = k;
                }
            }
            preIndex[i] = pre;
            dp[i] = max;
            if (max > largest) {
                largest = max;
                largestIndex = i;
            }
        }

        while (largestIndex >= 0) {
            result.add(0, nums[largestIndex]);
            largestIndex = preIndex[largestIndex];
        }
        return result;
    }
}
