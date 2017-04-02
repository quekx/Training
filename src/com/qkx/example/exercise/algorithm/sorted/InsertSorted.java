package com.qkx.example.exercise.algorithm.sorted;

/**
 * Created by qkx on 16/6/21.
 */
public class InsertSorted {
    // a[0] - a[i - 1] 为有序区
    // 每次将a[i]插入有序区
    public static void insertSort(int[] nums) {
        int n = nums.length;
        int i = 1;
        while (i <= n - 1) {
            int j = i;
            while (j >= 1) {
                if (nums[j - 1] < nums[j]) {
                    break;
                } else {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                }
                j--;
            }
            i++;
        }
    }
}
