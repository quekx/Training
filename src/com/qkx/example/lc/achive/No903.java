package com.qkx.example.lc.achive;

//You are given a string s of length n where s[i] is either:
//
//
// 'D' means decreasing, or
// 'I' means increasing.
//
//
// A permutation perm of n + 1 integers of all the integers in the range [0, n]
//is called a valid permutation if for all valid i:
//
//
// If s[i] == 'D', then perm[i] > perm[i + 1], and
// If s[i] == 'I', then perm[i] < perm[i + 1].
//
//
// Return the number of valid permutations perm. Since the answer may be large,
//return it modulo 10⁹ + 7.
//
//
// Example 1:
//
//
//Input: s = "DID"
//Output: 5
//Explanation: The 5 valid permutations of (0, 1, 2, 3) are:
//(1, 0, 3, 2)
//(2, 0, 3, 1)
//(2, 1, 3, 0)
//(3, 0, 2, 1)
//(3, 1, 2, 0)
//
//
// Example 2:
//
//
//Input: s = "D"
//Output: 1
//
//
//
// Constraints:
//
//
// n == s.length
// 1 <= n <= 200
// s[i] is either 'I' or 'D'.
//
//
// Related Topics String Dynamic Programming Prefix Sum 👍 657 👎 40


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.HashMap;
import java.util.Map;

/**
 * 解答成功:
 * 	执行耗时:989 ms,击败了8.16% 的Java用户
 * 	内存消耗:47.6 MB,击败了12.24% 的Java用户
 */
class No903 {

    public int numPermsDISequence(String s) {
        Map<String, Long> map = new HashMap<>();
        return (int) dfs(s, map);
    }

    private long dfs(String cur, Map<String, Long> map) {
        int mod = 1000_000_007;
        int n = cur.length();
        if (n == 1 || n == 0) {
            return 1;
        }
        if (map.containsKey(cur)) {
            return map.get(cur);
        }
        long num = 0;
        if (cur.charAt(0) == 'D') {
            num = (num + dfs(cur.substring(1), map)) % mod;
        }
        if (cur.charAt(n - 1) == 'I') {
            num = (num + dfs(cur.substring(0, n - 1), map)) % mod;
        }
        for (int i = 1; i <= cur.length() - 1; i++) {
            if (cur.charAt(i - 1) == 'I' && cur.charAt(i) == 'D') {
                long tmp = c(n, i);
                tmp = (tmp * dfs(cur.substring(0, i - 1), map)) % mod;
                tmp = (tmp * dfs(cur.substring(i + 1, n), map)) % mod;
//                num += c(n, i) * dfs(cur.substring(0, i), map) * dfs(cur.substring(i + 1, n), map);
                num = (num + tmp) % mod;
            }
        }
        map.put(cur, num);
        return num;
    }

    private long c(int a, int b) {
        int mod = 1000_000_007;
        long top = 1L, bottom = 1L;
        long ans = 1;
        for (int i = 0; i < b; i++) {
            top = (top * (a - i)) % mod;
            bottom = (bottom * (i + 1)) % mod;
        }
        // 实际数目 = top / bottom
        // 但是由于 top 和 bottom 是取余后的结果，不能直接相除
        // 除法取余转换成乘法，乘上分母的逆元
        // bottom 的逆元为 bottom ^ (p - 2)
        // 因为有费马小定理: bottom, p 互质且 p 为质数， bottom ^ (p - 1) mod p = 1
        // 则 bottom * bottom ^ (p - 2) mod p = 1
        // (top / bottom) mod p = (top * bottom ^ (p - 2)) mod p
        return (top * pow(bottom, mod - 2, mod)) % mod;
    }

    private long pow(long a, int n, int mod) {
        if (n == 1) {
            return a;
        }
        long x = pow(a, n / 2, mod);
        long ans = (x * x) % mod;
        ans = n % 2 == 0 ? ans : (ans * a) % mod;
        return ans;
    }

    public static void main(String[] args) {
        long mod = 1000_000_007;
        System.out.println(mod * mod * mod);

        String s = "IIDDIDDIIDDIDIDDIDDDDIIIDIDIDDDDDIIDIDDIDIIDIDDIIIDIIIIIIIIDIDIDIDDIDIIIIDDIIIIDDDDIIIDDIIDDIDIIIDDDDDDIIDIDDIIDDDDIIDDDIDIDDDIIIDIDIDIIIDDIDDDDDDDDIIDDIDDDIDDDIDDDDIIDIIIDDIDDDIDDIDDDIIDDIIIDIDIIDIDI";
        System.out.println(new No903().numPermsDISequence(s));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

