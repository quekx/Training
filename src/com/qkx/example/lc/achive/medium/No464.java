package com.qkx.example.lc.achive.medium;

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


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class No464 {

    public static void main(String[] args) {
        int maxChoosableInteger = 7;
        int desiredTotal = 16;
        System.out.println(new No464().canIWin2(maxChoosableInteger, desiredTotal));
    }

    /**
     * 解答成功: 执行耗时:906 ms,击败了35.57% 的Java用户 内存消耗:86.6 MB,击败了78.86% 的Java用户
     */
    public boolean canIWin2(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) {
            return true;
        }
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) {
            return false;
        }

        Map<Integer, Boolean> ans = new HashMap<>();
        return win(0, 0, maxChoosableInteger, desiredTotal, ans);
    }

    private boolean win(int curSum, int isUsed, int maxChoosableInteger, int desiredTotal, Map<Integer, Boolean> ans) {
        int key = (curSum << 21) | isUsed;
        if (ans.containsKey(key)) {
            return ans.get(key);
        }
        for (int i = 1; i <= maxChoosableInteger; i++) {
            if ((isUsed & (1 << i)) != 0) {
                continue;
            }
            if (curSum + i >= desiredTotal) {
                return true;
            }
            isUsed |= 1 << i;
            boolean nextWin = win(curSum + i, isUsed, maxChoosableInteger, desiredTotal, ans);
            isUsed &= ~(1 << i);
            if (!nextWin) {
                ans.put(key, true);
                return true;
            }
        }
        ans.put(key, false);
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
