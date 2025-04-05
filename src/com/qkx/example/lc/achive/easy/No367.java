package com.qkx.example.lc.achive.easy;

/**
 * Created by qkx on 17/6/30.
 */
public class No367 {
    public boolean isPerfectSquare(int num) {
        if (num < 0) return false;
        if (num == 0) return true;
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }
}
