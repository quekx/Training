package com.qkx.example.solutions.LeetCode.medium.x;

/**
 * Created by qkx on 16/11/11.
 */
public class No213 {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int r1 = rob(nums, 0, nums.length - 2);
        int r2 = rob(nums, 1, nums.length - 1);
        return Math.max(r1, r2);
    }

    private int rob(int[] nums, int start, int end) {
        if (start > end) return 0;

        int n = end - start + 1;
        if (n == 1) return nums[start];
        if (n == 2) return Math.max(nums[start], nums[start + 1]);

        int first = nums[start];
        int second = Math.max(nums[start], nums[start + 1]);

        int res = 0;
        for (int i = start + 2; i <= end; i++) {
            res = Math.max(first + nums[i], second);
            first = second;
            second = res;
        }
        return res;
    }
}
