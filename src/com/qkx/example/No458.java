package com.qkx.example;

/**
 * @author kaixin
 * @since 2022-04-21 16:56
 */
//There are buckets buckets of liquid, where exactly one of the buckets is
//poisonous. To figure out which one is poisonous, you feed some number of (poor) pigs
//the liquid to see whether they will die or not. Unfortunately, you only have
//minutesToTest minutes to determine which bucket is poisonous.
//
// You can feed the pigs according to these steps:
//
//
// Choose some live pigs to feed.
// For each pig, choose which buckets to feed it. The pig will consume all the
//chosen buckets simultaneously and will take no time.
// Wait for minutesToDie minutes. You may not feed any other pigs during this
//time.
// After minutesToDie minutes have passed, any pigs that have been fed the
//poisonous bucket will die, and all others will survive.
// Repeat this process until you run out of time.
//
//
// Given buckets, minutesToDie, and minutesToTest, return the minimum number of
//pigs needed to figure out which bucket is poisonous within the allotted time.
//
//
// Example 1:
// Input: buckets = 1000, minutesToDie = 15, minutesToTest = 60
//Output: 5
// Example 2:
// Input: buckets = 4, minutesToDie = 15, minutesToTest = 15
//Output: 2
// Example 3:
// Input: buckets = 4, minutesToDie = 15, minutesToTest = 30
//Output: 2
//
//
// Constraints:
//
//
// 1 <= buckets <= 1000
// 1 <= minutesToDie <= minutesToTest <= 100
//
// Related Topics Math Dynamic Programming Combinatorics 👍 630 👎 1149


//leetcode submit region begin(Prohibit modification and deletion)
class No458 {
    /**
     * dp[i][j]
     * 代表 i 只猪，j 桶水需要的最小校验次数
     * dp[i][j] = min(dp[i])
     *
     * @return
     */
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int count = minutesToTest / minutesToDie;
        int[][] dp = new int[buckets + 1][count + 1];

        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
