package com.qkx.example.lc.medium.x;

/**
 * Created by qkx on 16/11/24.
 */
public class No338 {
    public static int[] countBits(int num) {
        if (num == 0) return new int[]{0};

        int[] res = new int[num + 1];
        res[0] = 0;

//        int span = 2;
        int span = 1;
        for (int i = 1; i <= num; i++) {
            int temp = span << 1;
            if (i >= temp) {
                span = temp;
            }
            res[i] = res[i - span] + 1;
        }
        return res;
    }
}
