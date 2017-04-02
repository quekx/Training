package com.qkx.example.exercise.algorithm.sorted;

/**
 * Created by qkx on 16/6/21.
 */
public class SelectSorted {
    // 每次从无序区a[i] - a[n - 1]选择最小数
    // 与a[i] 交换并入有序区
    public static void selectSorted(int[] nums) {
        int n = nums.length;
        int i = 0;
        while (i <= n - 1) {
            // flag记录最小位置,初值为i
            int flag = i;
            // 从i + 1开始比较
            // 若大于n - 1则不进入循环, flag指向最后数
            int j = i + 1;
            while (j <= n - 1) {
                if (nums[j] < nums[flag]) {
                    flag = j;
                }
                j++;
            }

            int temp = nums[flag];
            nums[flag] = nums[i];
            nums[i] = temp;

            i++;
        }
    }
}
