package com.qkx.example.utils;

/**
 * Created by qkx on 16/6/12.
 */
public class NumberUtil {

    // 得到n个质数的数组
    public static int[] getPrimeNumbers(int n) {
        int[] primes = new int[n];
        primes[0] = 2;
        int cur = 3;
        int count = 1;

        while (count < n) {
            for (int num : primes) {
                if (num == 0 || num * num > cur) {
                    primes[count++] = cur++;
                    break;
                }
                if (cur % num == 0) {
                    cur++;
                    break;
                }
            }
        }
        return primes;
    }

    public static int[] getRandomNumbers(int n) {
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = (int) (Math.random() * 100);
        }
        return res;
    }

}
