package com.qkx.example.solutions.unfinish;

/**
 * Created by qkx on 17/3/4.
 */
public class No174 {
    /**
     The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

     The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

     Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

     In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.

     Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int rows = dungeon.length;
        int cols = dungeon[0].length;
        int[][] dp = new int[rows][cols];
        dp[0][0] = dungeon[0][0];
        int[][] min = new int[rows][cols];
        min[0][0] = dungeon[0][0];

        for (int row = 1; row < rows; row++) {
            dp[row][0] = dp[row - 1][0] + dungeon[row][0];
            min[row][0] = Math.min(min[row - 1][0], dp[row][0]);
        }

        for (int col = 1; col < cols; col++) {
            dp[0][col] = dp[0][col - 1] + dungeon[0][col];
            min[0][col] = Math.min(min[0][col - 1], dp[0][col]);
        }

        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
//                if (dp[row - 1][col] < dp[row])
                int curUp = dp[row - 1][col] + dungeon[row][col];
                int minUp = Math.min(min[row - 1][col], curUp);
                int curLeft = dp[row][col - 1] + dungeon[row][col];
                int minLeft = Math.min(min[row][col - 1], curLeft);
                if (minLeft < minUp) {
                    dp[row][col] = curUp;
                    min[row][col] = minUp;
                } else if (minLeft > minUp) {
                    dp[row][col] = curLeft;
                    min[row][col] = minLeft;
                } else {
                    dp[row][col] = Math.max(curUp, curLeft);
                    min[row][col] = minLeft;
                }
            }
        }

        int lowest = min[rows - 1][cols - 1];
        return lowest < 0 ? 1 - lowest : 1;
    }
}
