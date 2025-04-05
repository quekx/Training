package com.qkx.example.lc.achive.hard;

/**
 * Created by qkx on 16/5/30.
 */
public class No84 {
    public int largestRectangleArea1(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        return getMax(heights, 0, heights.length - 1);
    }
    private int getMax(int[] heights, int start, int end) {
        if (start == end) {
            return heights[start];
        }

        if (start + 1 == end) {
            return Math.min(heights[start], heights[end]) * 2;
        }

        int minIndex = start;
        for (int i = start + 1; i <= end; i++) {
            if (heights[i] < heights[minIndex]) {
                minIndex = i;
            }
        }
        int whole = heights[minIndex] * (end - start + 1);

        if (minIndex == start) {
            int right = getMax(heights, start + 1, end);
            return whole > right ? whole : right;
        }
        if (minIndex == end) {
            int left = getMax(heights, start, end - 1);
            return whole > left ? whole : left;
        }

        int left = getMax(heights, start, minIndex - 1);
        int right = getMax(heights, minIndex + 1, end);

        if (whole >= left && whole >= right) {
            return whole;
        }
        if (left >= whole && left >= right) {
            return left;
        }
        if (right >= whole && right >= left) {
            return right;
        }
        return 0;
    }

    public int largestRectangleArea2(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }



        return 0;
    }
}
