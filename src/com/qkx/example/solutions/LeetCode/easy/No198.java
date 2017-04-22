package com.qkx.example.solutions.LeetCode.easy;

/**
 * Created by qkx on 16/11/11.
 */
public class No198 {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);

        int res = 0;
        for (int i = 2; i < nums.length; i++) {
            res = Math.max(first + nums[i], second);
            first = second;
            second = res;
        }

        return res;
    }
}
