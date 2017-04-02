package com.qkx.example.exercise.algorithm.sorted;

/**
 * Created by qkx on 16/6/22.
 */
public class ShellSorted {
    public static void shellSorted(int[] nums) {
        int n = nums.length;
        int d = n / 2;
        while (d > 0) {
            for (int i = 0; i < d; i++) {
                // a[i]->a[i+d]->a[i+2d]
                int k = i;
                while (k < n) {
                    int flag = k;
                    int j = k + d;

                    while (j < n) {
                        if (nums[j] < nums[flag]) {
                            flag = j;
                        }
                        j += d;
                    }
                    int temp = nums[k];
                    nums[k] = nums[flag];
                    nums[flag] = temp;

                    k += d;
                }
            }
            d /= 2;
        }
    }
}
