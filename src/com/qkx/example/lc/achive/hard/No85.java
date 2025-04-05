package com.qkx.example.lc.achive.hard;

import java.util.Arrays;

/**
 * Created by qkx on 16/11/24.
 */
//Given a rows x cols binary matrix filled with 0's and 1's, find the largest re
//ctangle containing only 1's and return its area.
//
//
// Example 1:
//
//
//Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1",
//"1"],["1","0","0","1","0"]]
//Output: 6
//Explanation: The maximal rectangle is shown in the above picture.
//
//
// Example 2:
//
//
//Input: matrix = []
//Output: 0
//
//
// Example 3:
//
//
//Input: matrix = [["0"]]
//Output: 0
//
//
// Example 4:
//
//
//Input: matrix = [["1"]]
//Output: 1
//
//
// Example 5:
//
//
//Input: matrix = [["0","0"]]
//Output: 0
//
//
//
// Constraints:
//
//
// rows == matrix.length
// cols == matrix.length
// 0 <= row, cols <= 200
// matrix[i][j] is '0' or '1'.
//
// Related Topics Array Hash Table Dynamic Programming Stack
// 👍 3549 👎 77


//leetcode submit region begin(Prohibit modification and deletion)
public class No85 {
    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(new No85().maximalRectangle(matrix));
    }

    /**
     * height[m][n]标识以(m, n)为底，柱状型的高
     * 原问题可以转化为：
     * 遍历每个点(m, n)，计算以此点为左下角的矩形最大面积
     * 该矩形的最大面积，转换为问题：
     * height[i](0 <= i <= n)为第 m 行的柱状图，包含最右侧 height[n] 柱形的最大面积是多少
     * i 遍历 0 到 n，计算以 height[i] 为左侧，height[n] 柱形的矩形面积，取最大值
     * 该面积为 min(height[i], height[i+1], ..., height[n]) * (n - i + 1)
     * 最终结果在其中取最大值
     * 这一行的最大矩形面积：max(min(height[i], height[i+1], ..., height[n]) * (n - i + 1))
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        if (matrix[0].length == 0) return 0;

        int rowLen = matrix.length;
        int colLen = matrix[0].length;

        int[][] height = new int[rowLen][colLen];
        for (int col = 0; col < colLen; col++) {
            height[0][col] = matrix[0][col] == '1' ? 1 : 0;
        }

        for (int row = 1; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (matrix[row][col] == '1') {
                    height[row][col] = height[row - 1][col] + 1;
                } else {
                    height[row][col] = 0;
                }
            }
        }

        for (int[] h : height) {
            System.out.println(Arrays.toString(h));
        }
        int max = 0;
        // 遍历计算以(m, n)为右下角顶点的最大矩形
        for (int m = 0; m < rowLen; m++) {
            for (int n = 0; n < colLen; n++) {
                int minHeight = height[m][n];
                for (int k = n; k >= 0; k--) {
                    // 计算 height[k] ~ height[n]
                    // minHeight 为 height[k] ~ height[n] 的最小柱形高度
                    // 面积 = minHeight * (n - k + 1)
                    if (height[m][k] == 0) {
                        break;
                    }
                    minHeight = Math.min(minHeight, height[m][k]);
                    int s = minHeight * (n - k + 1);
                    max = Math.max(max, s);
                }
            }
        }

        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

