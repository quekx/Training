package com.qkx.example.solutions.LeetCode.medium;

/**
 * @author kaixin
 * @since 2020-11-10 15:05
 */
//A sequence of numbers is called arithmetic if it consists of at least three el
//ements and if the difference between any two consecutive elements is the same.
//
// For example, these are arithmetic sequences:
//
//
//1, 3, 5, 7, 9
//7, 7, 7, 7
//3, -1, -5, -9
//
// The following sequence is not arithmetic.
//
//
//1, 1, 2, 5, 7
//
//
// A zero-indexed array A consisting of N numbers is given. A slice of that arra
//y is any pair of integers (P, Q) such that 0 <= P < Q < N.
//
// A slice (P, Q) of the array A is called arithmetic if the sequence:
//A[P], A[P + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means t
//hat P + 1 < Q.
//
// The function should return the number of arithmetic slices in the array A.
//
//
// Example:
//
//
//A = [1, 2, 3, 4]
//
//return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4]
// itself.
//
// Related Topics Math Dynamic Programming
// 👍 1230 👎 176


//leetcode submit region begin(Prohibit modification and deletion)
public class No413 {
    public static int numberOfArithmeticSlices(int[] A) {
        int arrlen = A.length;
        if (arrlen <= 2) {
            return 0;
        }

        int result = 0;
        int p = 0;
        while (p <= arrlen - 3) {
            int maxDelta = 0;
            for (int q = p + 2; q <= arrlen - 1; q++) {
                if (A[q] - A[q - 1] == A[q - 1] - A[q - 2]) {
                    maxDelta = q - p;
                } else {
                    break;
                }
            }
            result += maxDelta * (maxDelta - 1) / 2;
            if (maxDelta == 0) {
                // 没有找到组合
                p++;
            } else {
                // 找到组合，直接跳到最后一位
                p = p + maxDelta;
            }
        }

        return result;
    }

    public static int numberOfArithmeticSlices2(int[] A) {
        int arrlen = A.length;
        if (arrlen <= 2) {
            return 0;
        }

        // isArithmetic[p][q]表示(p, q), 即A[p~q]是否arithmetic
        boolean[][] isArithmetic = new boolean[arrlen][arrlen];
        // step[p][q]表示A[p~q]为arithmetic时候的步长记录
        int[][] step = new int[arrlen][arrlen];
        // 初始化步长为(p, p + 1)
        for (int p = 0; p <= arrlen - 2; p++) {
            step[p][p + 1] = A[p + 1] - A[p];
            isArithmetic[p][p + 1] = true;
        }

        // q - p = len
        // 从区间2开始递增, [p, p + len]
        // isArithmetic[p, q] = isArithmetic[p, q - 1] && (A[q] - A[q - 1] == step[q - 1])
        for (int p = 0; p <= arrlen - 3; p++) {
            for (int delta = 2; p + delta <= arrlen - 1; delta++) {
                int q = p + delta;
                if (isArithmetic[p][q - 1] && (A[q] - A[q - 1] == step[p][q - 1])) {
                    isArithmetic[p][q] = true;
                    step[p][q] = step[p][q - 1];
                }
            }
        }

        int result = 0;
        for (int p = 0; p <= arrlen - 3; p++) {
            for (int delta = 2; p + delta <= arrlen - 1; delta++) {
                int q = p + delta;
                if (isArithmetic[p][q]) {
                    result++;
                }
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

