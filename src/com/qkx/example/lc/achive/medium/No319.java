package com.qkx.example.lc.achive.medium;

/**
 * Created by qkx on 17/6/4.
 */
public class No319 {
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }

    public int bulbSwitch1(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        int[] temp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            for (int index = i; index <= n; index += i) {
                temp[index]++;
            }
        }
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if ((temp[i] & 1) == 0) {
                result++;
            }
        }
        return result;
    }
}
