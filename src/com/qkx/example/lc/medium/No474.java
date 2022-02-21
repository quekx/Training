package com.qkx.example.lc.medium;

/**
 * @author kaixin
 * @since 2022-02-21 15:14
 */
//You are given an array of binary strings strs and two integers m and n.
//
// Return the size of the largest subset of strs such that there are at most m 0
//'s and n 1's in the subset.
//
// A set x is a subset of a set y if all elements of x are also elements of y.
//
//
// Example 1:
//
//
//Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
//Output: 4
//Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001",
// "1", "0"}, so the answer is 4.
//Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
//{"111001"} is an invalid subset because it contains 4 1's, greater than the
//maximum of 3.
//
//
// Example 2:
//
//
//Input: strs = ["10","0","1"], m = 1, n = 1
//Output: 2
//Explanation: The largest subset is {"0", "1"}, so the answer is 2.
//
//
//
// Constraints:
//
//
// 1 <= strs.length <= 600
// 1 <= strs[i].length <= 100
// strs[i] consists only of digits '0' and '1'.
// 1 <= m, n <= 100
//
// Related Topics Array String Dynamic Programming 👍 2653 👎 316


//leetcode submit region begin(Prohibit modification and deletion)
class No474 {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        for (int i = 1; i <= strs.length; i++) {
            int numOfZero = getNumOfZero(strs[i - 1]);
            int numOfOne = getNumOfOne(strs[i - 1]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (numOfZero > j || numOfOne > k) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - numOfZero][k - numOfOne] + 1);
                    }
                }
            }
        }
        return dp[strs.length][m][n];
    }

    private int getNumOfZero(String str) {
        int num = 0;
        for (char c : str.toCharArray()) {
            if (c == '0') {
                num++;
            }
        }
        return num;
    }

    private int getNumOfOne(String str) {
        int num = 0;
        for (char c : str.toCharArray()) {
            if (c == '1') {
                num++;
            }
        }
        return num;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
