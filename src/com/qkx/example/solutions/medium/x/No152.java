package com.qkx.example.solutions.medium.x;

/**
 * Created by qkx on 16/11/1.
 */
public class No152 {
    public static int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int res = 0;

        int pos = 0;
        int nag = 0;

        int tempPos;
        int tempNag;
        for (int num : nums) {
            if (num > 0) {
                tempPos = pos == 0 ? num : num * pos;
                tempNag = nag == 0 ? 0 : num * nag;
            } else if (num < 0) {
                tempPos = nag == 0 ? 0 : num * nag;
                tempNag = pos == 0 ? num : num * pos;
            } else {
                tempPos = 0;
                tempNag = 0;
            }

            pos = tempPos;
            nag = tempNag;
            res = Math.max(res, pos);
        }
        return res;
    }
}
