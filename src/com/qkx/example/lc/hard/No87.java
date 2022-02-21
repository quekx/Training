package com.qkx.example.lc.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kaixin
 * @since 2020-12-02 15:41
 */
//We can scramble a string s to get a string t using the following algorithm:
//
//
// If the length of the string is 1, stop.
// If the length of the string is > 1, do the following:
//
// Split the string into two non-empty substrings at a random index, i.e., if th
//e string is s, divide it to x and y where s = x + y.
// Randomly decide to swap the two substrings or to keep them in the same order.
// i.e., after this step, s may become s = x + y or s = y + x.
// Apply step 1 recursively on each of the two substrings x and y.
//
//
//
//
// Given two strings s1 and s2 of the same length, return true if s2 is a scramb
//led string of s1, otherwise, return false.
//
//
// Example 1:
//
//
//Input: s1 = "great", s2 = "rgeat"
//Output: true
//Explanation: One possible scenario applied on s1 is:
//"great" --> "gr/eat" // divide at random index.
//"gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and
// keep them in order.
//"gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both subs
//trings. divide at ranom index each of them.
//"g/r / e/at" --> "r/g / e/at" // random decision was to swap the first substri
//ng and to keep the second substring in the same order.
//"r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, divi
//de "at" to "a/t".
//"r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substring
//s in the same order.
//The algorithm stops now and the result string is "rgeat" which is s2.
//As there is one possible scenario that led s1 to be scrambled to s2, we return
// true.
//
//
// Example 2:
//
//
//Input: s1 = "abcde", s2 = "caebd"
//Output: false
//
//
// Example 3:
//
//
//Input: s1 = "a", s2 = "a"
//Output: true
//
//
//
// Constraints:
//
//
// s1.length == s2.length
// 1 <= s1.length <= 30
// s1 and s2 consist of lower-case English letters.
//
// Related Topics String Dynamic Programming
// 👍 668 👎 765


//leetcode submit region begin(Prohibit modification and deletion)
public class No87 {
    public static void main(String[] args) {
        String s1 = "ccabcbabcbabbbbcbb";
        String s2 = "bbbbabccccbbbabcba";
        System.out.println(new No87().isScramble(s1, s2));
    }

    /**
     * 将字符 s1 切割成两段 s11,s12 后
     * 无论子串 s11, s12 后续怎么切割，s1 和 s2 都不会相互影响
     * 如果 s11/s12 和 s2 对应的位置字符集合不一致，那么将永远不一致
     *
     * 将 s1 切割成 (0, i) (i + 1, n)两段 0 <= i <= n - 1
     * 防止切成空字符，i <= n - 1
     *
     * 判断比较两种情况
     * 1. 不交换：s1[0, i] <--> s2[0, i], s1[i + 1, n] <--> s2[i + 1, n]
     * 这两段字符集合是否一致
     * 1). 如果不一致必然不成立
     * 2). 如果一致，继续判断子串
     *
     * 2. 交换: s1[0, i] <--> s2[n - i, n], s1[i + 1, n] <--> s2[0, n - i - 1]
     * 这两段字符集合是否一致
     * 1). 如果不一致必然不成立
     * 2). 如果一致，继续判断子串
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        Map<String, Boolean> cache = new HashMap<>();
        return check(s1, 0, s1.length() - 1, s2, 0, s2.length() - 1, cache);
    }

    private boolean check(String s1, int start1, int end1,
                          String s2, int start2, int end2,
                          Map<String, Boolean> cache) {
        if (start1 == end1) {
            return s1.charAt(start1) == s2.charAt(start2);
        }

        String key = start1 + "_" + end1 + "_" + start2 + "_" + end2;
        Boolean cacheResult = cache.get(key);
        if (cacheResult != null) {
            return cacheResult;
        }

        for (int i = 0; i <= end1 - start1 - 1; i++) {
            // 1. 不交换
            if (check(s1, start1, start1 + i, s2, start2, start2 + i, cache)
                    && check(s1, start1 + i + 1, end1, s2, start2 + i + 1, end2, cache)) {
                cache.put(key, true);
                return true;
            }
            // 2. 交换
            if (check(s1, start1, start1 + i, s2, end2 - i ,end2, cache)
                    && check(s1, start1 + i + 1, end1, s2, start2, end2 - i - 1, cache)) {
                cache.put(key, true);
                return true;
            }
        }
        cache.put(key, false);
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

