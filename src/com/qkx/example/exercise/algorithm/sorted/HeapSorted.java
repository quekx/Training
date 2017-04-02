package com.qkx.example.exercise.algorithm.sorted;

/**
 * Created by qkx on 16/6/21.
 */
public class HeapSorted {
    // 插入建堆,自顶向下 >>> O(n * lg(n))
    // 普通建堆,自底向上 >>> O(n)

    // 最大堆--->升序
    public static void heapSorted(int[] nums) {
        int n = nums.length;
//        for (int i = n - 1; i >= 0; i--) {
//            // 向下调整堆化  ===> n
//            heapFixDownMax(nums, i, n - 1);
//        }
        for (int i = 1; i <= n - 1; i++) {
            // 向上调整堆化  ==> n * lg(n)
            heapFixUpMax(nums, i);
        }
        for (int i = n - 1; i >= 1; i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;

            heapFixDownMax(nums, 0, i - 1);
        }
    }

    // 删除调整cf
    // 从i节点开始,从上向下调整
    // 与子节点作比较
    // i节点,子节点分别为2*i+1, 2*i+2;
    private static void heapFixDownMax(int[] nums, int i, int n) {
        int x = nums[i];
        int j = 2 * i + 1;
        while (j <= n) {
            // 找出子节点中最大的:j
            if (j + 1 <= n && nums[j] < nums[j + 1]) {
                j++;
            }
            if (x < nums[j]) {
                nums[i] = nums[j];
                i = j;
                j = 2 * i + 1;
            } else {
                break;
            }
        }
        nums[i] = x;
    }

    // 插入调整
    // 从i节点开始,从下向上调整
    // 与父节点做比较
    // i节点,父节点为(i-1)/2
    private static void heapFixUpMax(int[] nums, int i) {
        int x = nums[i];
        int j = (i - 1) / 2;
        while (j >= 0 && i > 0) {
            if (x > nums[j]) {
                nums[i] = nums[j];
                i = j;
            } else {
                break;
            }
            j = (i - 1) / 2;
        }
        nums[i] = x;
    }
}
