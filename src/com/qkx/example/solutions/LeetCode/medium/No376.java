package com.qkx.example.solutions.LeetCode.medium;

/**
 * Created by qkx on 17/7/6.
 */
public class No376 {
    /**
     * 子序列上下翻转
     *
     * Input: [1,7,4,9,2,5]
     * Output: 6
     * The entire sequence is a wiggle sequence.
     *
     * Input: [1,17,5,10,13,15,10,5,16,8]
     * Output: 7
     * There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
     *
     * Input: [1,2,3,4,5,6,7,8,9]
     * Output: 2
     */

    public int wiggleMaxLength(int[] nums) {
        if (nums == null) return 0;
        if (nums.length <= 1) return nums.length;

        int len = nums.length;
        int[] dpUp = new int[len];
        dpUp[0] = 1;
        int[] dpDown = new int[len];
        dpDown[0] = 1;

        int result = 1;
        for (int i = 1; i <= len - 1; i++) {
            int max = 1;
            int maxUp = 1;
            int maxDown = 1;
            for (int k = 0; k <= i - 1; k++) {
                if (nums[i] > nums[k]) {
                    int temp = dpDown[k] + 1;
                    maxUp = Math.max(maxUp, temp);
                    max = Math.max(max, temp);
                } else if (nums[i] < nums[k]) {
                    int temp = dpUp[k] + 1;
                    maxDown = Math.max(maxDown, temp);
                    max = Math.max(max, temp);
                }
            }
            dpDown[i] = maxDown;
            dpUp[i] = maxUp;
            result = Math.max(result, max);
        }
        return result;
    }
}
