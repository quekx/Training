package com.qkx.example.lc.achive.medium;

import java.util.Arrays;

/**
 * @author kaixin
 * @since 2022-02-22 15:07
 */
//Given an m x n matrix mat, return an array of all the elements of the array
//in a diagonal order.
//
//
// Example 1:
//
//
//Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
//Output: [1,2,4,7,5,3,6,8,9]
//
//
// Example 2:
//
//
//Input: mat = [[1,2],[3,4]]
//Output: [1,2,3,4]
//
//
//
// Constraints:
//
//
// m == mat.length
// n == mat[i].length
// 1 <= m, n <= 10⁴
// 1 <= m * n <= 10⁴
// -10⁵ <= mat[i][j] <= 10⁵
//
// Related Topics Array Matrix Simulation 👍 1968 👎 505


//leetcode submit region begin(Prohibit modification and deletion)
class No498 {

    public static void main(String[] args) {
        int[][] mat = {{1,2,3},{4,5,6},{7,8,9}};
        int[] res = new No498().findDiagonalOrder(mat);
        System.out.println(Arrays.toString(res));
    }

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] result = new int[m * n];

        int curX = 0;
        int curY = 0;
        boolean isUp = true;
        for (int i = 0; i < m * n; i++) {
            result[i] = mat[curX][curY];
            if (isUp) {
                if (curX - 1 >= 0 && curY + 1 <= n - 1) {
                    curX--;
                    curY++;
                } else if (curY + 1 == n) {
                    isUp = false;
                    curX++;
                } else {
                    isUp = false;
                    curY++;
                }
            } else {
                if (curX + 1 <= m - 1 && curY - 1 >= 0) {
                    curX++;
                    curY--;
                } else if (curX + 1 == m) {
                    isUp = true;
                    curY++;
                } else {
                    isUp = true;
                    curX++;
                }
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

