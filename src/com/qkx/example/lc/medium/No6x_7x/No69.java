package com.qkx.example.lc.medium.No6x_7x;

/**
 * Created by qkx on 16/5/26.
 */
public class No69 {
    public static int mySqrt(int x) {

        if (x < 0) {
            return -1;
        }
        if (x == 0 || x == 1) {
            return x;
        }

        double x1 = x;
        double x2 = x / 2;
        while (Math.abs(x1 - x2) > 0.01) {
            x1 = x2;
            x2 = (x1 + x / x1) / 2;
        }
        int res = (int) x1;
        if (res * res > x) {
            return res - 1;
        } else {
            return res;
        }
    }
}
