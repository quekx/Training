package com.qkx.example.solutions.LeetCode.easy;

/**
 * Created by qkx on 16/11/17.
 */
public class No303 {

    /**
     * sums[0] = 0
     * sums[1] = nums[0]
     * sums[2] = nums[0] + nums[1]
     * sums[i] ==> nums[0] ~ nums[i - 1]
     */
    public class NumArray {
        private int[] sums;

        public NumArray(int[] nums) {
            sums = new int[nums.length + 1];
            sums[0] = 0;
            for (int i = 1; i <= nums.length; i++) {
                sums[i] = sums[i - 1] + nums[i - 1];
            }
        }

        public int sumRange(int i, int j) {
            return sums[j + 1] - sums[i];
        }
    }
}
