package com.qkx.example.lc.easy;

/**
 * Created by qkx on 17/4/6.
 */
public class No190 {
    public static int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = result << 1;
            if ((n & 1) == 1) {
                result++;
            }
            n = n >> 1;
        }
        return result;
    }
}
