package com.qkx.example.exercise.algorithm.bit;

import com.qkx.example.utils.ArrayUtil;

public class BinaryIndexedTree {

    private static int lowBit(int x) {
        int ans = x & -x;
        System.out.printf("%s & %s >> %s\n", Integer.toBinaryString(x), Integer.toBinaryString(-x), Integer.toBinaryString(ans));
        return ans;
    }

    private static void add(int i, long v, int[] values) {
        i += 1;
        while (i < values.length) {
            values[i] += v;
            i += lowBit(i);
        }
    }

    private static long query(int i, int[] values) {
        i += 1;
        long ans = 0;
        while (i > 0) {
            ans += values[i];
            i -= lowBit(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 10;
        int[] values = new int[n + 1];
        ArrayUtil.print(values);
        add(2, 2, values);
//        System.out.println(Integer.toBinaryString(9));
//        System.out.println(Integer.toBinaryString(lowBit(9)));

        ArrayUtil.print(values);
    }
}
