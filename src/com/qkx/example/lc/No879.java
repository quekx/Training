package com.qkx.example.lc;

//There is a group of n members, and a list of various crimes they could commit.
// The iᵗʰ crime generates a profit[i] and requires group[i] members to
//participate in it. If a member participates in one crime, that member can't participate
//in another crime.
//
// Let's call a profitable scheme any subset of these crimes that generates at
//least minProfit profit, and the total number of members participating in that
//subset of crimes is at most n.
//
// Return the number of schemes that can be chosen. Since the answer may be
//very large, return it modulo 10⁹ + 7.
//
//
// Example 1:
//
//
//Input: n = 5, minProfit = 3, group = [2,2], profit = [2,3]
//Output: 2
//Explanation: To make a profit of at least 3, the group could either commit
//crimes 0 and 1, or just crime 1.
//In total, there are 2 schemes.
//
// Example 2:
//
//
//Input: n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
//Output: 7
//Explanation: To make a profit of at least 5, the group could commit any
//crimes, as long as they commit one.
//There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).
//
//
//
// Constraints:
//
//
// 1 <= n <= 100
// 0 <= minProfit <= 100
// 1 <= group.length <= 100
// 1 <= group[i] <= 100
// profit.length == group.length
// 0 <= profit[i] <= 100
//
//
// Related Topics Array Dynamic Programming 👍 1263 👎 93


import com.qkx.example.utils.ArrayUtil;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 解答成功:
 * 	执行耗时:128 ms,击败了23.97% 的Java用户
 * 	内存消耗:59.3 MB,击败了15.75% 的Java用户
 */
class No879 {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int mod = 1000_000_000 + 7;
        long[][][] f = new long[group.length][n + 1][minProfit + 1];
        for (int j = 0; j <= n; j++) {
            for (int k = 0; k <= minProfit; k++) {
                f[0][j][k] = group[0] <= j && profit[0] >= k ? 1 : 0;
            }
        }
        for (int i = 1; i < group.length; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    f[i][j][k] = f[i - 1][j][k];
                    if (group[i] <= j) {
                        if (k - profit[i] > 0) {
                            f[i][j][k] += f[i - 1][j - group[i]][k - profit[i]];
                        } else {
                            f[i][j][k] += f[i - 1][j - group[i]][0] + 1;
                        }
                    }
                    f[i][j][k] %= mod;
                }
            }
        }
        ArrayUtil.print(f[group.length - 1]);
        ArrayUtil.print(f[group.length - 2]);
        return minProfit == 0 ? (int) f[group.length - 1][n][minProfit] + 1 : (int) f[group.length - 1][n][minProfit];
    }

    /**
     * f[1][5][3] = f[0][3][0] + 1 + f[0][5][3]
     * @param args
     */
    public static void main(String[] args) {
        int n = 5;
        int minProfit = 3;
        int[] g = {2, 2};
        int[] p = {2, 3};
        System.out.println(new No879().profitableSchemes(n, minProfit, g, p));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
