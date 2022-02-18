package com.qkx.example;

//In the "100 game" two players take turns adding, to a running total, any
//integer from 1 to 10. The player who first causes the running total to reach or
//exceed 100 wins.
//
// What if we change the game so that players cannot re-use integers?
//
// For example, two players might take turns drawing from a common pool of
//numbers from 1 to 15 without replacement until they reach a total >= 100.
//
// Given two integers maxChoosableInteger and desiredTotal, return true if the
//first player to move can force a win, otherwise, return false. Assume both
//players play optimally.
//
//
// Example 1:
//
//
//Input: maxChoosableInteger = 10, desiredTotal = 11
//Output: false
//Explanation:
//No matter which integer the first player choose, the first player will lose.
//The first player can choose an integer from 1 up to 10.
//If the first player choose 1, the second player can only choose integers from
//2 up to 10.
//The second player will win by choosing 10 and get a total = 11, which is >=
//desiredTotal.
//Same with other integers chosen by the first player, the second player will
//always win.
//
//
// Example 2:
//
//
//Input: maxChoosableInteger = 10, desiredTotal = 0
//Output: true
//
//
// Example 3:
//
//
//Input: maxChoosableInteger = 10, desiredTotal = 1
//Output: true
//
//
//
// Constraints:
//
//
// 1 <= maxChoosableInteger <= 20
// 0 <= desiredTotal <= 300
//
// Related Topics Math Dynamic Programming Bit Manipulation Memoization Game
//Theory Bitmask 👍 1743 👎 280


import com.qkx.example.utils.ArrayUtil;

//leetcode submit region begin(Prohibit modification and deletion)
class No464 {

    public static void main(String[] args) {
        int maxChoosableInteger = 10;
        int desiredTotal = 11;
        System.out.println(new No464().canIWin(maxChoosableInteger, desiredTotal));
    }

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) {
            return true;
        }
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }

        boolean[][] dp = new boolean[maxChoosableInteger + 1][desiredTotal + 1];
        // dp[1][j]
        dp[1][1] = true;
        for (int j = 2; j <= desiredTotal; j++) {
            for (int i = 2; i <= maxChoosableInteger; i++) {
                if (i >= j) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = dp[i - 1][j] || !dp[i - 1][j - i];
                }
            }
        }
        ArrayUtil.print(dp);
        return dp[maxChoosableInteger][desiredTotal];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
