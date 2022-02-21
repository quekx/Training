package com.qkx.example.lc.medium.x;

/**
 * Created by qkx on 17/4/16.
 */
public class No223 {

    /**
     * Find the total area covered by two rectilinear rectangles in a 2D plane.
     * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
     */
    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {

        // [A, C] && [E, G]
        int width = findCommonLength(A, C, E, G);
        // [B, D] && [F, H]
        int height = findCommonLength(B, D, F, H);


        return calculateArea(A, B, C, D) + calculateArea(E, F, G, H) - width * height;
    }

    private static int calculateArea(int A, int B, int C, int D) {
        return (C - A) * (D - B);
    }

    private static int findCommonLength(int left1, int right1, int left2, int right2) {
        if (right1 < left2 || right2 < left1) return 0;

        int left = Math.max(left1, left2);
        int right = Math.min(right1, right2);
        return Math.abs(right - left);
    }
}
