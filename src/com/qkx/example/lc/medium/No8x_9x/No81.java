package com.qkx.example.lc.medium.No8x_9x;

/**
 * Created by qkx on 16/5/27.
 */
public class No81 {
    public boolean search(int[] nums, int target) {

        int min = 0;
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (nums[i] < nums[i - 1]) {
                min = i;
                break;
            }
        }

        int left = min;
        int right = min + nums.length - 1;
        int middle;
        while (left <= right) {
            middle = (left + right) / 2;
            int m = middle % len;
            if (nums[m] == target) {
                return true;
            } else if (nums[m] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return false;
    }
}
