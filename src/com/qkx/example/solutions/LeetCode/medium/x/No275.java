package com.qkx.example.solutions.LeetCode.medium.x;

/**
 * Created by qkx on 17/5/1.
 */
public class No275 {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;

        int left = 0;
        int right = citations.length;
        int n = citations.length;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(citations[mid] == n - mid) return n - mid;
            if(citations[mid] < citations.length - mid) left = mid + 1;
            else right = mid;
        }
        return n - left;
    }
}
