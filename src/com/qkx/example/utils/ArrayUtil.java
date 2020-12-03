package com.qkx.example.utils;

import java.util.Arrays;

/**
 * @author kaixin
 * @since 2020-12-03 19:31
 */
public class ArrayUtil {
    public static void print(boolean[][] matrix) {
        for (boolean[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void print(int[][] nums) {
        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }
    }

    public static void print(int[] nums) {
        System.out.println(Arrays.toString(nums));
    }
}
