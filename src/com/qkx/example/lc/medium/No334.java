package com.qkx.example.lc.medium;

/**
 * Created by qkx on 17/6/23.
 */
public class No334 {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length <= 2) return false;

        int small = Integer.MAX_VALUE;
        int middle = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num > middle) {
                return true;
            } else if (num > small) {
                middle = num;
            } else if (num < small) {
                small = num;
            }
        }

        return false;
    }
}
