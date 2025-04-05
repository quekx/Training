package com.qkx.example.lc.achive.easy.No6x_7x;

/**
 * Created by qkx on 16/5/26.
 */
public class No70 {
    public static int climbStairs(int n) {
        int[] ref = new int[n + 1];
        ref[1] = 1;
        ref[2] = 2;
        for (int i = 3; i <= n; i++) {
            ref[i] = ref[i - 1] + ref[i - 2];
        }
        return ref[n];
    }
}
