package com.qkx.example.lc.achive.medium;

/**
 * @author kaixin
 * @since 2022-02-22 11:15
 */
//You are given an integer array nums. Two players are playing a game with this
//array: player 1 and player 2.
//
// Player 1 and player 2 take turns, with player 1 starting first. Both players
//start the game with a score of 0. At each turn, the player takes one of the
//numbers from either end of the array (i.e., nums[0] or nums[nums.length - 1]) which
//reduces the size of the array by 1. The player adds the chosen number to their
//score. The game ends when there are no more elements in the array.
//
// Return true if Player 1 can win the game. If the scores of both players are
//equal, then player 1 is still the winner, and you should also return true. You
//may assume that both players are playing optimally.
//
//
// Example 1:
//
//
//Input: nums = [1,5,2]
//Output: false
//Explanation: Initially, player 1 can choose between 1 and 2.
//If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If
//player 2 chooses 5, then player 1 will be left with 1 (or 2).
//So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
//Hence, player 1 will never be the winner and you need to return false.
//
//
// Example 2:
//
//
//Input: nums = [1,5,233,7]
//Output: true
//Explanation: Player 1 first chooses 1. Then player 2 has to choose between 5
//and 7. No matter which number player 2 choose, player 1 can choose 233.
//Finally, player 1 has more score (234) than player 2 (12), so you need to
//return True representing player1 can win.
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 20
// 0 <= nums[i] <= 10⁷
//
// Related Topics Array Math Dynamic Programming Recursion Game Theory 👍 2702 ?
//? 142


//leetcode submit region begin(Prohibit modification and deletion)
class No486 {
    /**
     * dp[i][k]  0 <= i <= k <= len - 1
     * 表示 num[i, k] 取的最大值
     *
     * @param nums
     * @return
     */
    public boolean PredictTheWinner(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int[][] sum = new int[nums.length][nums.length];
        int[][] dp = new int[nums.length][nums.length];
        for (int i = 0; i <= nums.length - 1; i++) {
            sum[i][i] = nums[i];
            dp[i][i] = nums[i];
        }
        for (int i = 0; i <= nums.length - 1; i++) {
            for (int k = i + 1; k <= nums.length - 1; k++) {
                sum[i][k] = sum[i][k - 1] + nums[k];
            }
        }

        for (int delta = 1; delta <= nums.length - 1; delta++) {
            for (int i = 0; i + delta <= nums.length - 1; i++) {
                // i, i + delta
                dp[i][i + delta] =
                        Math.max(
                                nums[i] + (sum[i + 1][i + delta] - dp[i + 1][i + delta]),
                                nums[i + delta] + (sum[i][i + delta - 1] - dp[i][i + delta - 1])
                        );
            }
        }
        return dp[0][nums.length - 1] * 2 >= sum[0][nums.length - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

