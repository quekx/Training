package com.qkx.example.lc;

//Given an integer n, return the number of prime numbers that are strictly less
//than n.
//
//
// Example 1:
//
//
//Input: n = 10
//Output: 4
//Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
//
//
// Example 2:
//
//
//Input: n = 0
//Output: 0
//
//
// Example 3:
//
//
//Input: n = 1
//Output: 0
//
//
//
// Constraints:
//
//
// 0 <= n <= 5 * 10⁶
//
//
// Related Topics Array Math Enumeration Number Theory 👍 6525 👎 1247


//leetcode submit region begin(Prohibit modification and deletion)
class No204 {
    /**
     * 解答成功:
     * 	执行耗时:144 ms,击败了47.32% 的Java用户
     * 	内存消耗:47.2 MB,击败了29.21% 的Java用户
     */
    public int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        int ans = 0;
        boolean[] f = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (f[i]) {
                continue;
            }
            ans++;
            for (int x = i; x < n; x += i) {
                f[x] = true;
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

