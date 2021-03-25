package com.qkx.example.solutions.other;

import java.util.Arrays;
import java.util.Random;

/**
 * @author kaixin
 * @since 2021-03-25 11:14
 */
public class RGBSort {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            f();
            System.out.println("check time >> " + i);
        }
    }

    private static void f() {
        Random random = new Random();
        int len = 100;
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = random.nextInt(4);
        }

        nums = new int[]{3, 1, 3, 2, 0, 1, 2, 0, 0, 2, 3, 2, 0, 0, 1, 2, 3, 3, 1, 1, 0, 1, 1, 2, 3, 1, 0, 2, 0, 1, 1, 2, 3, 1, 3, 0, 3, 2, 0, 2, 3, 3, 3, 3, 3, 3, 0, 1, 2, 2, 3, 1, 1, 2, 1, 0, 2, 2, 3, 3, 3, 0, 3, 2, 3, 2, 2, 2, 1, 0, 0, 0, 3, 1, 1, 0, 3, 3, 1, 2, 0, 1, 0, 1, 0, 2, 0, 0, 2, 3, 1, 1, 0, 2, 0, 0, 2, 1, 3, 1};
        System.out.println(Arrays.toString(nums));

        sort2(nums);
        System.out.println(Arrays.toString(nums));

        for (int i = 0; i <= len - 2; i++) {
            if (nums[i] > nums[i + 1]) {
                System.out.println("error i >> " + i);
                throw new RuntimeException("error!");
            }
        }
    }

    private static void sort(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int x = 0;
        int c = 0;
        int y = nums.length - 1;
        while (c < nums.length && c <= y) {
            if (nums[c] == 0) {
                int t = nums[c];
                nums[c] = nums[x];
                nums[x] = t;
                c++;
                x++;
            } else if (nums[c] == 1) {
                c++;
            } else if (nums[c] == 2) {
                int t = nums[c];
                nums[c] = nums[y];
                nums[y] = t;
                y--;
            } else {
                throw new RuntimeException("xxx");
            }
        }
        System.out.println("x >> " + x + ", c >> " + c + ", y >> " + y);
    }

    private static void sort2(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int x = 0;
        int cur = 0;
        int y = nums.length - 1;
        int z = nums.length - 1;
        while (cur < nums.length && cur <= y) {
            if (nums[cur] == 0) {
                swap(cur, x, nums);
                cur++;
                x++;
            } else if (nums[cur] == 1) {
                cur++;
            } else if (nums[cur] == 2) {
                swap(cur, y, nums);
                y--;
            } else if (nums[cur] == 3) {
                // 交换时要考虑
                // 1. cur和y是否重合
                // 2: y和z是否重合
//                swap(y, z, nums);
//                swap(cur, z, nums);
                if (z == y) {
                    swap(cur, z, nums);
                } else {
                    swap(cur, z, nums);
                    swap(cur, y, nums);
                }
                y--;
                z--;
            } else {
                throw new RuntimeException("xxx");
            }
        }
    }

    private static void swap(int a , int b, int[] nums) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }
}
