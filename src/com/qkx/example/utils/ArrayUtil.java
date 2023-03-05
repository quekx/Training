package com.qkx.example.utils;

import java.util.Arrays;

/**
 * @author kaixin
 * @since 2020-12-03 19:31
 */
public class ArrayUtil {
    public static void print(boolean[][] matrix) {
        String[][] out = new String[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                out[i][j] = matrix[i][j] ? "V" : "X";
            }
        }

        System.out.println("============");
        for (String[] row : out) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void print(int[][] nums) {
        System.out.println("============");
        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }
    }

    public static void print(long[][] nums) {
        System.out.println("============");
        for (long[] num : nums) {
            System.out.println(Arrays.toString(num));
        }
    }

    public static void print(int[] nums) {
        System.out.println(Arrays.toString(nums));
    }

    public static void print(long[] nums) {
        System.out.println(Arrays.toString(nums));
    }

    public static void print(boolean[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void print(double[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
