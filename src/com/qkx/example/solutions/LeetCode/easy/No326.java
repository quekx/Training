package com.qkx.example.solutions.LeetCode.easy;

/**
 * Created by qkx on 17/6/12.
 */
public class No326 {
    public boolean isPowerOfThree(int n) {
        return (n > 0 && 1162261467 % n == 0);
    }
}
