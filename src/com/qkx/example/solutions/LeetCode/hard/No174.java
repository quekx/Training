package com.qkx.example.solutions.LeetCode.hard;

import com.qkx.example.utils.ArrayUtil;

/**
 * Created by qkx on 17/3/4.
 */
//
// The demons had captured the princess (P) and imprisoned her in the bottom-rig
//ht corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D gri
//d. Our valiant knight (K) was initially positioned in the top-left room and must
// fight his way through the dungeon to rescue the princess.
//
// The knight has an initial health point represented by a positive integer. If
//at any point his health point drops to 0 or below, he dies immediately.
//
// Some of the rooms are guarded by demons, so the knight loses health (negative
// integers) upon entering these rooms; other rooms are either empty (0's) or cont
//ain magic orbs that increase the knight's health (positive integers).
//
// In order to reach the princess as quickly as possible, the knight decides to
//move only rightward or downward in each step.
//
//
//
// Write a function to determine the knight's minimum initial health so that he
//is able to rescue the princess.
//
// For example, given the dungeon below, the initial health of the knight must b
//e at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
//
//
//
//
// -2 (K)
// -3
// 3
//
//
// -5
// -10
// 1
//
//
// 10
// 30
// -5 (P)
//
//
//
//
//
//
// Note:
//
//
// The knight's health has no upper bound.
// Any room can contain threats or power-ups, even the first room the knight ent
//ers and the bottom-right room where the princess is imprisoned.
//
// Related Topics Binary Search Dynamic Programming
// 👍 2017 👎 46


//leetcode submit region begin(Prohibit modification and deletion)
public class No174 {
    public static void main(String[] args) {
        int[][] dungeon = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        ArrayUtil.print(dungeon);
        System.out.println();
        System.out.println(new No174().calculateMinimumHP(dungeon));
    }

    /**
     * dp[i][j] 代表从(i,j)开始，能到达右下的最小初始值
     * 向左最小初始值 min(dp[i+1][j] - b[i+1][j], 1)
     * 向下最小初始值 min(dp[i][j+1] - b[i][j+1], 1)
     * dp[i][j] = min(dp[i+1][j] - b[i+1][j])
     *
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];

        dp[m - 1][n - 1] = 1;
        for (int row = m - 2; row >= 0; row--) {
            dp[row][n - 1] = Math.max(dp[row + 1][n - 1] - dungeon[row + 1][n - 1], 1);
        }

        for (int col = n - 2; col >= 0; col--) {
            dp[m - 1][col] = Math.max(dp[m - 1][col + 1] - dungeon[m - 1][col + 1], 1);
        }

        for (int row = m - 2; row >= 0; row--) {
            for (int col = n - 2; col >= 0; col--) {
                dp[row][col] = Math.min(dp[row + 1][col] - dungeon[row + 1][col],
                        dp[row][col + 1] - dungeon[row][col + 1]);
                dp[row][col] = Math.max(dp[row][col], 1);
            }
        }

        return Math.max(dp[0][0] - dungeon[0][0], 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

