package com.qkx.example.lc.easy;

/**
 * Created by qkx on 17/6/23.
 */
public class No342 {
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num & 0xAAAAAAAA) == 0;
    }
}
