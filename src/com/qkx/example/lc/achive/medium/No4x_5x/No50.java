package com.qkx.example.lc.achive.medium.No4x_5x;

/**
 * Created by qkx on 16/5/20.
 */
public class No50 {
    /**
     * Implement pow(x, n).
     */
    public double myPow(double x, int n) {
        long m = n;
        return m >= 0 ? pow(x, m) : 1 / pow(x, -m);
    }

    private double pow(double x, long n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        double a = pow(x, n / 2);
        double ans = a * a;
        return n % 2 == 0 ? ans : ans * x;
    }
}
