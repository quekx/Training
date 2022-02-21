package com.qkx.example.lc.medium.No8x_9x;

/**
 * Created by qkx on 16/5/27.
 */
public class No80 {
    public int removeDuplicates(int[] nums) {

        /**
         * You are here!
         * Your runtime beats 62.51% of javasubmissions.
         */
        int i = 1;
        int j = 0;

        boolean isDouble = false;
        while (i < nums.length) {
            if (nums[i] == nums[j]) {
                if (!isDouble) {
                    nums[++j] = nums[i];
                    isDouble = true;
                }
            } else {
                nums[++j] = nums[i];
                isDouble = false;
            }
            i++;
        }

        return j + 1;
    }
}
