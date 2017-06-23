package com.qkx.example.solutions.LeetCode.medium.x;

/**
 * Created by qkx on 17/5/1.
 */
public class No274 {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;

        int len = citations.length;
        int[] times = new int[len + 1];
        for (int citation : citations) {
            if (citation > len) citation = len;
            times[citation]++;
        }

        int temp = 0;
        for (int i = len; i >= 0; i--) {
            temp += times[i];
            if (temp >= i) {
                return i;
            }
        }
        return 0;
    }
}
