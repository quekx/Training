package com.qkx.example.exercise.algorithm.sorted;

/**
 * Created by qkx on 16/6/21.
 */
public class QuickSorted {
    public static void quickSorted(int[] nums, int left, int right) {
        if (left < right) {
            int pos = partitionX(nums, left, right);
            quickSorted(nums, left, pos - 1);
            quickSorted(nums, pos + 1, right);
        }
    }

    private static int partitionX(int[] nums, int left, int right) {
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

    private static int partitionY(int[] nums, int left, int right) {
        int index = left + 1;
        for (int i = index; i <= right; i++) {
            if (nums[i] < nums[left]) {
                swap(nums, i, index);
                index++;
            }
        }
        swap(nums, left, index - 1);
        return index - 1;
    }

    private static void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }
}
