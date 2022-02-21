package com.qkx.example.lc.easy;

/**
 * Created by qkx on 17/4/16.
 */
public class No230 {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;

        int x = n & -n;
        return x == n;
    }
}
