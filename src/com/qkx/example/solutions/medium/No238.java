package com.qkx.example.solutions.medium;

/**
 * Created by qkx on 17/3/24.
 */
public class No238 {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return null;

        int n = nums.length;
        int[] res = new int[n];
        res[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            res[i] = res[i + 1] * nums[i + 1];
        }

        int left = 1;
        for (int i = 0; i < n; i++) {
            res[i] *= left;
            left *= nums[i];
        }

        return res;
    }
}
