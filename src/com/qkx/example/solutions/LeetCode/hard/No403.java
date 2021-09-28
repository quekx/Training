package com.qkx.example.solutions.LeetCode.hard;

/**
 * @author kaixin
 * @since 2021-09-28 11:44
 */
//A frog is crossing a river. The river is divided into some number of units,
//and at each unit, there may or may not exist a stone. The frog can jump on a
//stone, but it must not jump into the water.
//
// Given a list of stones' positions (in units) in sorted ascending order,
//determine if the frog can cross the river by landing on the last stone. Initially,
//the frog is on the first stone and assumes the first jump must be 1 unit.
//
// If the frog's last jump was k units, its next jump must be either k - 1, k,
//or k + 1 units. The frog can only jump in the forward direction.
//
//
// Example 1:
//
//
//Input: stones = [0,1,3,5,6,8,12,17]
//Output: true
//Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd
//stone, then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3
//units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.
//
//
// Example 2:
//
//
//Input: stones = [0,1,2,3,4,8,9,11]
//Output: false
//Explanation: There is no way to jump to the last stone as the gap between the
//5th and 6th stone is too large.
//
//
//
// Constraints:
//
//
// 2 <= stones.length <= 2000
// 0 <= stones[i] <= 2³¹ - 1
// stones[0] == 0
// stones is sorted in a strictly increasing order.
//
// Related Topics Array Dynamic Programming 👍 1934 👎 144


//leetcode submit region begin(Prohibit modification and deletion)
public class No403 {

    public static void main(String[] args) {
        int[] stones = {0,1,3,5,6,8,12,17};
        System.out.println(new No403().canCross(stones));
    }

    /**
     * dp[i][k] ( 1 <= i <= n - 1, 1 <= k <= n - 1)
     * 代表 到达 num[i]，前一跳步长为 k 能够到达
     *
     * @param stones
     * @return
     */
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) {
            return false;
        }
        if (stones.length == 1) {
            return true;
        }
        if (stones[0] != 0 || stones[1] != 1) {
            return false;
        }

        int len = stones.length;
        boolean dp[][] = new boolean[len][len];
        dp[1][1] = true;
        for (int i = 1; i <= len - 1; i++) {
            for (int k = 1; k <= len - 1; k++) {
                if (!dp[i][k]) {
                    continue;
                }
                // 说明能到达num[i]
                // 对比 num[i] + k - 1/num[i] + k/num[i] + k + 1
                int next = i + 1;
                while (next <= len - 1
                        && (stones[next] <= stones[i] + k - 1
                        || stones[next] <= stones[i] + k
                        || stones[next] <= stones[i] + k + 1)) {
                    if (stones[next] == stones[i] + k - 1) {
                        dp[next][k - 1] = true;
                    }
                    if (stones[next] == stones[i] + k) {
                        dp[next][k] = true;
                    }
                    if (stones[next] == stones[i] + k + 1) {
                        dp[next][k + 1] = true;
                    }
                    next++;
                }
            }
        }

        for (int k = 1; k <= len - 1; k++) {
            if (dp[len - 1][k]) {
                return true;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

