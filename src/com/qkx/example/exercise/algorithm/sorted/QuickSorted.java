package com.qkx.example.exercise.algorithm.sorted;

/**
 * Created by qkx on 16/6/21.
 */
public class QuickSorted {
    public static void quickSorted(int[] nums, int left, int right) {
        if (left < right) {
            int pos = sortedStep(nums, left, right);
            quickSorted(nums, left, pos - 1);
            quickSorted(nums, pos + 1, right);
        }
    }
    private static int sortedStep(int[] nums, int left, int right) {
        int i = left;
        int j = right;
        int x = nums[left];
        while (i < j) {
            while (i < j && nums[j] >= x) {
                j--;
            }
            if (i < j) {
                nums[i] = nums[j];
            }

            while (i < j && nums[i] <= x) {
                i++;
            }
            if (i < j) {
                nums[j] = nums[i];
            }
        }
        nums[i] = x;
        return i;
    }
}
