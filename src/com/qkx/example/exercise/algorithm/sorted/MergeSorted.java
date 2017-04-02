package com.qkx.example.exercise.algorithm.sorted;

/**
 * Created by qkx on 16/6/21.
 */
public class MergeSorted {
    public static void mergeSorted(int[] nums, int left, int right, int[] temp) {
        int middle = (left + right) / 2;
        if (left < middle) {
            mergeSorted(nums, left, middle, temp);
        }
        if (middle + 1 < right) {
            mergeSorted(nums, middle + 1, right, temp);
        }
        if (left < right) {
            merge(nums, left, middle, right, temp);
        }
    }
    private static void merge(int[] nums, int l, int m, int r, int[] temp) {
        int i = l;
        int j = m + 1;
        int k = 0;
        while (i <= m || j <= r) {
            if (i <= m && j <= r) {
                if (nums[i] < nums[j]) {
                    temp[k++] = nums[i++];
                } else {
                    temp[k++] = nums[j++];
                }
            } else if (i <= m) {
                temp[k++] = nums[i++];
            } else if (j <= r){
                temp[k++] = nums[j++];
            }
        }
        for (int index = 0; index < k; index++) {
            nums[l + index] = temp[index];
        }
    }
}
