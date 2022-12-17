package com.qkx.example.lc.hard;

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
     * f(i, m) 代表 i 只猪，经过 m 轮可以验证多少桶水
     * i 只猪经过一轮后
     * 1). 全活情况：1 种，剩余 i 只猪，这部分集合可以分配 f(i, m-1) 桶水
     * 2). 死一只情况：i 种，剩余 i-1 只猪，这部分集合可分配 f(i-1, m-1) 桶水
     * ...
     * 3). 死 i-1 只情况，i 种，剩余 1 只猪，这部分可分配 f(1, m-1) 桶水
     * 4). 全死情况：1 种，这块的集合只能分配 1 桶水，因为没有猪剩余来验证 f(0, m-1) = 1
     */
    /**
     * 以 2 只，2 轮为例
     * 一轮过后，可能存在 3 种情况
     * 1). 全活，1 种情况，剩余 2 只猪的情况下，剩余 1 轮还可以验证 4 桶水, 即 f(2, 1) = 4
     * 2). 死 1 只，2 种情况，剩余 1 轮还可以验证 2 桶水，即 f(1, 1) = 2
     * 3). 全死，1 种情况，剩余 1 轮无法在集合内继续验证，因此只能集合大小只能为 1, f(0, 1) = 1
     * f(2, 2) = f(2, 1) + 2 * f(1, 1) + f(0, 1) = 4 + 2 * 2 + 1 = 9
     *
     * 结论：2 只猪 2轮最多验证 9 桶水
     * 具体分配方案：水桶 1~9
     * 第一只猪：(1) + (2,3)
     * 第二只猪：(1) + (4,5)
     * 1) 如果 1,2 全死了，说明 (1) 有毒
     * 2) 如果 1 死了，说明 (2,3) 内有毒，剩余 1 只猪继续验证
     * 3) 同上 2)
     * 4) 如果 1,2 都没死, 说明 (6,7,8,9) 内有毒，剩余 2 只猪继续验证
     */
    /**
     * 表达式
     * f(i, m) = Sum{C(k, i) * f(k, m-1)}
     * 数学归纳证明可得 f(i, m) = (m+1)^i
     * (1) 假设 f(i, m-1) = m^i。 一式
     * (2) 指数展开 (m+1)^i = Sum{C(k, i) * m^k * 1^(i-k)} = Sum{C(k, i) * m^k}
     * 代入一式得 (m+1)^i = Sum{C(k, i) * f(k, m-1)} = f(i, m)
     * 得 f(i, m) = (m+1)^i
     */
    /**
     * 解答成功: 执行耗时:0 ms,击败了100.00% 的Java用户 内存消耗:39.2 MB,击败了84.70% 的Java用户
     */
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        if (buckets == 1) {
            return 0;
        }
        int count = 1;
        int m = minutesToTest / minutesToDie;
        int tmp = m + 1;
        while (tmp < buckets) {
            count++;
            tmp *= m + 1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new No458().poorPigs(1000, 15, 60));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
