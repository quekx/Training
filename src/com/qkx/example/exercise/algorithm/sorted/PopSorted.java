package com.qkx.example.exercise.algorithm.sorted;

/**
 * Created by qkx on 16/6/20.
 */
public class PopSorted {
    // basic
    public static void sorted(int[] nums) {
        int n = nums.length;
        int i = 0;
        while (i <= n - 2) {
            int j = n - 1;
            // nums[j] compare nums[j - 1]
            while (j >= i + 1) {
                if (nums[j - 1] > nums[j]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                }
                j--;
            }
            i++;
        }
    }

    // better
    public static void popSortedWithFlag(int[] nums) {
        int n = nums.length;
        int i = 0;
        // 某一次未发生交换,则已经有序
        boolean flag;
        while (i <= n - 2) {
            flag = true;
            int j = n - 1;
            // nums[j] compare nums[j - 1]
            while (j >= i + 1) {
                if (nums[j - 1] > nums[j]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;

                    if (flag) {
                        flag = false;
                    }
                }
                j--;
            }
            if (flag) {
                return;
            }
            i++;
        }
    }

    //best
    public static void popSortedWithLastPos(int[] nums) {
        int n = nums.length;
        int i = 0;
        // 某一次未发生交换,则已经有序
        boolean flag;
        // 上一趟第一次发生交换的位置j
        int lastPos = n - 2;
        boolean isFirst;
        while (i <= n - 2) {
            flag = true;
            isFirst = true;
            int j = lastPos + 1;
            // 在末尾发生交换时会超出边界
            // 依旧比较末尾
            j = j > n - 1 ? n - 1 : j;
            // nums[j] compare nums[j - 1]
            while (j >= i + 1) {
                if (nums[j - 1] > nums[j]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;

                    if (flag) {
                        flag = false;
                    }

                    if (isFirst) {
                        lastPos = j;
                        isFirst = false;
                    }
                }
                j--;
            }
            if (flag) {
                return;
            }
            i++;
        }
    }
}
