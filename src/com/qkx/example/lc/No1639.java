package com.qkx.example.lc;

//You are given a list of strings of the same length words and a string target.
//
//
// Your task is to form target using the given words under the following rules:
//
//
//
// target should be formed from left to right.
// To form the iᵗʰ character (0-indexed) of target, you can choose the kᵗʰ
//character of the jᵗʰ string in words if target[i] = words[j][k].
// Once you use the kᵗʰ character of the jᵗʰ string of words, you can no longer
//use the xᵗʰ character of any string in words where x <= k. In other words, all
//characters to the left of or at index k become unusuable for every string.
// Repeat the process until you form the string target.
//
//
// Notice that you can use multiple characters from the same string in words
//provided the conditions above are met.
//
// Return the number of ways to form target from words. Since the answer may be
//too large, return it modulo 10⁹ + 7.
//
//
// Example 1:
//
//
//Input: words = ["acca","bbbb","caca"], target = "aba"
//Output: 6
//Explanation: There are 6 ways to form target.
//"aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("caca")
//"aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("caca")
//"aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("acca")
//"aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("acca")
//"aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("acca")
//"aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("caca")
//
//
// Example 2:
//
//
//Input: words = ["abba","baab"], target = "bab"
//Output: 4
//Explanation: There are 4 ways to form target.
//"bab" -> index 0 ("baab"), index 1 ("baab"), index 2 ("abba")
//"bab" -> index 0 ("baab"), index 1 ("baab"), index 3 ("baab")
//"bab" -> index 0 ("baab"), index 2 ("baab"), index 3 ("baab")
//"bab" -> index 1 ("abba"), index 2 ("baab"), index 3 ("baab")
//
//
//
// Constraints:
//
//
// 1 <= words.length <= 1000
// 1 <= words[i].length <= 1000
// All strings in words have the same length.
// 1 <= target.length <= 1000
// words[i] and target contain only lowercase English letters.
//
//
// Related Topics Array String Dynamic Programming 👍 437 👎 28


import com.qkx.example.utils.ArrayUtil;

import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class No1639 {

    /**
     * 解答成功:
     * 	执行耗时:90 ms,击败了74.56% 的Java用户
     * 	内存消耗:58.4 MB,击败了37.65% 的Java用户
     */
    public int numWays(String[] words, String target) {
        int wl = words[0].length();
        long[][] dp = new long[wl + 1][target.length() + 1];
        for (int i = 0; i <= wl; i++) {
            dp[i][0] = 1;
        }
        // counter[i][j] 记录 words 数组中每个字符串位置 [i] 的字符统计
        int[][] counter = new int[wl][26];
        for (String word : words) {
            for (int i = 0; i < wl; i++) {
                counter[i][word.charAt(i) - 'a']++;
            }
        }
        int mod = 1000_000_000 + 7;
        for (int i = 1; i <= wl; i++) {
            for (int j = 1; j <= target.length(); j++) {
                char ch = target.charAt(j - 1);
                dp[i][j] = (dp[i - 1][j] + counter[i][ch - 'a'] * dp[i - 1][j - 1]) % mod;
            }
        }
        return (int) dp[wl][target.length()];
    }

    /**
     * dp[i][j] 代表 words[x][0...i] 能匹配 t[0...j] 的方案数目
     * Time Limit Exceeded
     * O(word.length * target.length * len(words))
     */
    public int numWays2(String[] words, String target) {
        int wl = words[0].length();
        long[][] dp = new long[wl + 1][target.length() + 1];
        for (int i = 0; i <= wl; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= wl; i++) {
            for (int j = 1; j <= target.length(); j++) {
                dp[i][j] = dp[i - 1][j];
                for (String word : words) {
                    if (word.charAt(i - 1) == target.charAt(j - 1)) {
                        dp[i][j] += dp[i - 1][j - 1];
                        dp[i][j] %= 1000_000_000 + 7;
                    }
                }
            }
        }
        return (int) dp[wl][target.length()];
    }

    public static void main(String[] args) {
        String[] w = {"abba","baab"};
        String t = "bab";
        System.out.println(new No1639().numWays(w, t));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
