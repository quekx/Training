package com.qkx.example.solutions.medium.No8x_9x;

/**
 * Created by qkx on 16/9/17.
 */
public class No96 {
    public static int numTrees(int n) {
        if (n == 0) {
            return 1;
        }

        int[] nums = new int[n + 1];
        nums[0] = 1;
        nums[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j * 2 < i - 1; j++) {
                nums[i] += nums[j] * nums[i - 1 - j] * 2;
            }
            if ((i - 1) % 2 == 0) {
                int side = (i - 1) / 2;
                nums[i] += nums[side] * nums[side];
            }
        }
        return nums[n];
    }
}
