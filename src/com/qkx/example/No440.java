package com.qkx.example;

/**
 * @author kaixin
 * @since 2022-04-18 16:24
 */
//Given two integers n and k, return the kᵗʰ lexicographically smallest integer
//in the range [1, n].
//
//
// Example 1:
//
//
//Input: n = 13, k = 2
//Output: 10
//Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7
//, 8, 9], so the second-smallest number is 10.
//
//
// Example 2:
//
//
//Input: n = 1, k = 1
//Output: 1
//
//
//
// Constraints:
//
//
// 1 <= k <= n <= 10⁹
//
// Related Topics Trie 👍 552 👎 73


//leetcode submit region begin(Prohibit modification and deletion)
class No440 {
    public int findKthNumber(int n, int k) {
        int i = 1;
        while (i < 10) {
            int prefixNum = getPrefixNum(n, i);
            if (k <= prefixNum) {
                break;
            } else {
                k -= prefixNum;
                i++;
            }
        }
        return getNum(n, i, k)[0];
    }

    /**
     * 返回二元数组
     * 第一元素为满足的第 k 个结果
     * 第二元素为如果没找到第 k 个结果，返回该前缀下的数个数
     */
    private int[] getNum(int n, int start, int k) {
        if (start > n) {
            return new int[]{-1, 0};
        }

        if (k == 1) {
            return new int[]{start, 1};
        }

        int count = 1;
        k -= 1;
        for (int i = 0; i <= 9; i++) {
            int[] next = getNum(n, start * 10 + i, k);
            int ans = next[0], num = next[1];
            if (ans > 0) {
                return new int[]{ans, -1};
            }
            k -= num;
            count += num;
        }
        return new int[]{-1, count};
    }

    private int getPrefixNum(int n, long prefix) {
        if (prefix > n) {
            return 0;
        }

        int ans = 1;
        for (int i = 0; i <= 9; i++) {
            ans += getPrefixNum(n, prefix * 10 + i);
        }
        return ans;
    }

    public static void main(String[] args) {
        long t = System.nanoTime();
        System.out.println(new No440().getPrefixNum(804289384, 1));
        System.out.println((System.nanoTime() - t) / 1000_000 + "ms");

//        int n = 804289384, k = 42641503;
//        System.out.println(new No440().findKthNumber(804289384, 42641503));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

