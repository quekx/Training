package com.qkx.example.solutions.LeetCode.medium;

/**
 * Created by qkx on 17/6/29.
 */
public class No365 {
    /**
     * You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available.
     * You need to determine whether it is possible to measure exactly z litres using these two jugs.
     * If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.
     * Input: x = 3, y = 5, z = 4
     * Output: True
     */

    public boolean canMeasureWater(int x, int y, int z) {
        if (x < 0 || y < 0 || z < 0 || x + y < z) return false;
        if (x == z || y == z || x + y == z) return true;

        return z % gcb(x, y) == 0;
    }

    private int gcb(int a, int b) {
        if (a < b) {
            int t = b;
            b = a;
            a = t;
        }
        while (b != 0) {
            int x = a % b;
            a = b;
            b = x;
        }
        return a;
    }
}
