package com.qkx.example.solutions.pancake;

import java.util.Arrays;
import java.util.Random;

/**
 * @author kaixin
 * @since 2021-04-12 上午10:23
 *
 * 烧饼排序是个很有意思的实际问题：
 * 假设盘子上有 n 块面积大小不一的烧饼，
 * 你如何用一把锅铲进行若干次翻转，
 * 让这些烧饼的大小有序（小的在上，大的在下）？
 */
public class PancakeSort {

    public static void main(String[] args) {
        int[] pancakes = new int[10];
        for (int i = 0; i < pancakes.length; i++) {
            pancakes[i] = new Random().nextInt(10000);
        }
        new PancakeSort().sort(pancakes);
        System.out.println(Arrays.toString(pancakes));
    }

    /**
     * 不是最小翻转次数的解法
     * 每次找对最大值
     * 1. 先将其翻转到队头
     * 2. 然后翻转到队尾
     * @param pancakes
     */
    public void sort(int[] pancakes) {
        if (pancakes.length == 1) {
            return;
        }

        for (int i = pancakes.length - 1; i >= 1; i--) {
            int maxIndex = findMaxIndex(pancakes, i);
            reverse(pancakes, maxIndex);
            reverse(pancakes, i);
        }
    }

    private int findMaxIndex(int[] pancakes, int end) {
        int max = pancakes[0];
        int maxIndex = 0;
        for (int i = 1; i <= end; i++) {
            if (pancakes[i] > max) {
                max = pancakes[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private void reverse(int[] pancakes, int end) {
        int start = 0;
        while (start < end) {
            swap(pancakes, start, end);
            start++;
            end--;
        }
    }

    private void swap(int[] pancakes, int a, int b) {
        int t = pancakes[a];
        pancakes[a] = pancakes[b];
        pancakes[b] = t;
    }
}
