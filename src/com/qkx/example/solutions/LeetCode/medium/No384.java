package com.qkx.example.solutions.LeetCode.medium;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by qkx on 17/7/9.
 */
public class No384 {
    /**
     * Shuffle a set of numbers without duplicates.
     */
}

class Solution {

    private int[] origin;
    private int[] temp;
    private Random random;

    public Solution(int[] nums) {
        this.origin = nums;
        this.temp = Arrays.copyOf(nums, nums.length);
        this.random = new Random();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return origin;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        for (int i = 1; i <= temp.length - 1; i++) {
            // [0, i - 1] + i
            swap(temp, random.nextInt(i + 1), i);
        }
        return temp;
    }

    private void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }
}