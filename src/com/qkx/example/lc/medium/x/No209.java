package com.qkx.example.lc.medium.x;

/**
 * Created by qkx on 17/3/23.
 */
public class No209 {
    /**
     * Given an array of n positive integers and a positive integer s,
     * find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
     * For example, given the array [2,3,1,2,4,3] and s = 7,
     * the subarray [4,3] has the minimal length under the problem constraint.
     */
    public static int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (s <= 0) return 1;

        int n = nums.length;
        int sum = 0;
        int start = 0;
        while (start < n && sum + nums[start] < s) {
            sum += nums[start++];
        }
        if (start == n) return 0;

        int left = 0;
        sum += nums[start];
        while (left <= start && sum - nums[left] >= s) {
            sum -= nums[left++];
        }

        int res = start - left + 1;
        for (int right = start + 1; right < n; right++) {
            sum += nums[right];
            while (left <= right && sum - nums[left] >= s) {
                sum -= nums[left++];
            }

            res = Math.min(res, right - left + 1);
        }

        return res;
    }
}
