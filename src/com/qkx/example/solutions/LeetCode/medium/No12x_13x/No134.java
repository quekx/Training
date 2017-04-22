package com.qkx.example.solutions.LeetCode.medium.No12x_13x;

/**
 * Created by qkx on 17/3/15.
 */
public class No134 {
    public static int canCompleteCircuit(int[] gas, int[] cost) {

        int n = gas.length;

        int len ;
        int i = 0;
        int remain;
        for (; i < n; i += len) {
            remain = 0;
            for (len = 1; len <= n; len++) {
                int index = (i + len - 1) % n;
                remain += gas[index] - cost[index];
                if (remain < 0) {
                    break;
                }
            }
            if (len > n) return i;
        }
        return -1;
    }

    public int xx(int[] gas, int[] cost) {
        int n = gas.length;
        int j = 1;
        int i = 1;
        for (i = 0; i < n; i += j) {// short cut
            int rest = 0;
            for (j = 0; j < n; j++) {
                int index = (i + j) % n;
                rest += gas[index] - cost[index];
                if (rest < 0) {
                    break;
                }
            }
            if (j == n) {
                return i;
            }
        }
        return -1;
    }
}
