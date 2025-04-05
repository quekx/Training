package com.qkx.example.lc.achive.medium;//Write a program to find the nth super ugly number.
//
// Super ugly numbers are positive numbers whose all prime factors are in the gi
//ven prime list primes of size k.
//
// Example:
//
//
//Input: n = 12, primes = [2,7,13,19]
//Output: 32
//Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12
//
//             super ugly numbers given primes = [2,7,13,19] of size 4.
//
// Note:
//
//
// 1 is a super ugly number for any given primes.
// The given numbers in primes are in ascending order.
// 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
// The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
//
// Related Topics Math Heap
// 👍 623 👎 141


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
public class No313 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Arrays.toString(args));

        System.out.println(new No313().nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}));

        Thread.sleep(1000000L);
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n == 1) {
            return 1;
        }

        if (primes == null || primes.length == 0) {
            return 0;
        }

        int[] dp = new int[n + 1];
        int[] base = new int[primes.length];
        for (int i = 0; i <= primes.length - 1; i++) {
            base[i] = 1;
        }
        dp[1] = 1;
        for (int k = 2; k <= n; k++) {
            int targetValue = Integer.MAX_VALUE;
            int primesIndex = 0;
            for (int i = 0; i <= primes.length - 1; i++) {
                int baseIndex = base[i];
                int value = dp[baseIndex] * primes[i];
                if (value < targetValue) {
                    targetValue = value;
                    primesIndex = i;
                }
            }
            base[primesIndex]++;
            dp[k] = targetValue;
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
