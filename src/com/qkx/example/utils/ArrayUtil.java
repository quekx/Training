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
                out[i][j] = matrix[i][j] ? "1" : "0";
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

    public static boolean equal(int[] nums1, int[] nums2) {
        if (nums1.length != nums2.length) {
            System.out.println("not equal!");
            return false;
        }
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] != nums2[i]) {
                System.out.println("not equal!");
                return false;
            }
        }
        System.out.println("equal");
        return true;
    }
}
