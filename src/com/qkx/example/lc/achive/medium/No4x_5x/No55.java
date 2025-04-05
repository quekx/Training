package com.qkx.example.lc.achive.medium.No4x_5x;

/**
 * Created by qkx on 16/5/24.
 */
public class No55 {
    /**
     * time limit
     */
    public static boolean canJump1(int[] nums) {
        if (nums.length == 1) {
            return true;
        }

        return move(nums, 0);
    }
    private static boolean move(int[] nums, int pos) {
        if (nums[pos] == 0) {
            return false;
        }
        if (pos + nums[pos] >= nums.length - 1) {
            return true;
        }
        for (int i = pos + 1; i <= pos + nums[pos]; i++) {
            if (nums[i] > 0) {
                if (move(nums, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * AC 2ms
     */
    public static boolean canJump2(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        if (nums[0] == 0) {
            return false;
        }

        int i = nums.length - 2;
        while (i >= 0) {
            if (nums[i] == 0) {
                boolean isExist = false;
                for (int j = i - 1; j >= 0; j--) {
                    if (j + nums[j] > i) {
                        isExist = true;
                        i = j - 1;
                        break;
                    }
                }
                if (!isExist) {
                    return false;
                }
            } else {
                i--;
            }
        }
        return true;
    }
}
