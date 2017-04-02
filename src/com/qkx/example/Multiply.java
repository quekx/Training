package com.qkx.example;

/**
 * Created by qkx on 16/5/28.
 */
public class Multiply {
    public static int multiply(int a, int b) {
        int res = 0;
        int flag = 1 << 30;

        while (flag > 0) {
            if ((flag & b) != 0) {
                res = (res << 1) + a;
            } else {
                res = res << 1;
            }
            flag = flag >> 1;
        }

        return res;
    }
}
