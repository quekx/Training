package com.qkx.example.lc.achive.medium;

/**
 * @author kaixin
 * @since 2022-02-21 14:24
 */
//You are given an integer array matchsticks where matchsticks[i] is the length
//of the iᵗʰ matchstick. You want to use all the matchsticks to make one square.
//You should not break any stick, but you can link them up, and each matchstick
//must be used exactly one time.
//
// Return true if you can make this square and false otherwise.
//
//
// Example 1:
//
//
//Input: matchsticks = [1,1,2,2,2]
//Output: true
//Explanation: You can form a square with length 2, one side of the square came
//two sticks with length 1.
//
//
// Example 2:
//
//
//Input: matchsticks = [3,3,3,3,4]
//Output: false
//Explanation: You cannot find a way to form a square with all the matchsticks.
//
//
//
// Constraints:
//
//
// 1 <= matchsticks.length <= 15
// 1 <= matchsticks[i] <= 10⁸
//
// Related Topics Array Dynamic Programming Backtracking Bit Manipulation
//Bitmask 👍 1496 👎 117


//leetcode submit region begin(Prohibit modification and deletion)
class No473 {

    public static void main(String[] args) {
        int[] matchsticks = {1,1,2,2,2};
        System.out.println(new No473().makesquare(matchsticks));
    }

    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int stick : matchsticks) {
            sum += stick;
        }
        if (sum % 4 != 0) {
            return false;
        }

        int target = sum / 4;
        int[] dp = new int[1 << matchsticks.length];
        return make(matchsticks, target, target, 0, dp);
    }

    private boolean make(int[] matchsticks, int target, int remain, int mask, int[] dp) {
        if (dp[mask] != 0) {
            return dp[mask] == 1;
        }

        if ((mask + 1) == (1 << matchsticks.length)) {
            return true;
        }

        for (int i = 0; i <= matchsticks.length - 1; i++) {
            if ((mask & (1 << i)) != 0) {
                continue;
            }
            int newMask = mask | (1 << i);
            if (remain == matchsticks[i] && make(matchsticks, target, target, newMask, dp)) {
                dp[mask] = 1;
                return true;
            }
            if (remain > matchsticks[i] && make(matchsticks, target, remain - matchsticks[i], newMask, dp)) {
                dp[mask] = 1;
                return true;
            }
        }
        dp[mask] = -1;
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

