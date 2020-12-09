package com.qkx.example.solutions.LeetCode.unfinish;

import java.util.*;

/**
 * @author kaixin
 * @since 2020-12-04 10:21
 */
//Given a non-empty string s and a dictionary wordDict containing a list of non-
//empty words, add spaces in s to construct a sentence where each word is a valid
//dictionary word. Return all such possible sentences.
//
// Note:
//
//
// The same word in the dictionary may be reused multiple times in the segmentat
//ion.
// You may assume the dictionary does not contain duplicate words.
//
//
// Example 1:
//
//
//Input:
//s = "catsanddog"
//wordDict = ["cat", "cats", "and", "sand", "dog"]
//Output:
//[
//  "cats and dog",
//  "cat sand dog"
//]
//
//
// Example 2:
//
//
//Input:
//s = "pineapplepenapple"
//wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
//Output:
//[
//  "pine apple pen apple",
//  "pineapple pen apple",
//  "pine applepen apple"
//]
//Explanation: Note that you are allowed to reuse a dictionary word.
//
//
// Example 3:
//
//
//Input:
//s = "catsandog"
//wordDict = ["cats", "dog", "sand", "and", "cat"]
//Output:
//[]
// Related Topics Dynamic Programming Backtracking
// 👍 2668 👎 426


//leetcode submit region begin(Prohibit modification and deletion)
public class No140 {
    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        List<String> wordDict = Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
        List<String> x = new No140().wordBreak3(s, wordDict);
        System.out.println(x);
    }

    /**
     * 防止重复计算，记录每个字符串分隔结果 str -> [s1, s2]
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : wordDict) {
            List<String> list = new LinkedList<>();
            list.add(word);
            map.put(word, list);
        }
        return null;
    }

    /**
     * dp[i] 表示 s[0, i] 匹配分隔的 字符创结果列表
     * s[0, i] 分隔 s[0, k] + s[k + 1, i]; 0 <= k <= i - 1
     * 如果 s[k + 1, i] 在字典中
     * dp[i] 加上下面结果
     * dp[i] += dp[k] 叉乘 s[k + 1, i]
     *
     * 运行失败: Memory Limit Exceeded
     * 测试用例:
     * "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
     * ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"] stdout:
     */
    public List<String> wordBreak3(String s, List<String> wordDict) {
        List<List<String>> dp = new ArrayList<>(s.length());
        int x = 0;
        for (int i = 0; i <= s.length() - 1; i++) {
            List<String> list = new LinkedList<>();
            // s[0, i]
            String si = s.substring(0, i + 1);
            if (wordDict.contains(si)) {
                list.add(si);
            }

            for (int k = 0; k <= i - 1; k++) {
                System.out.println(++x);
                String sk = s.substring(k + 1, i + 1);
                if (wordDict.contains(sk)) {
                    System.out.println(sk);
                    List<String> pre = dp.get(k);
                    for (String preStr : pre) {
                        list.add(preStr + " " + sk);
                    }
                }
            }
            dp.add(list);
        }
        return dp.get(s.length() - 1);
    }

    /**
     * 运行失败: Time Limit Exceeded
     * 递归有很多重复计算，比如 "a" "a" + remain  <==> "aa" + remain
     * remain 剩余部分都是重复计算
     * 测试用例:
     * "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
     * ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"] stdout:
     */
    public List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> res = new LinkedList<>();
        divide2(s, 0, s.length() - 1, "", wordDict, res);
        return res;
    }

    private void divide2(String s, int start, int end, String result, List<String> wordDict, List<String> res) {
        if (start == end + 1) {
            res.add(result.substring(1));
        }

        for (String word : wordDict) {
            if (!startWith2(s, start, end, word)) {
                continue;
            }

            String curRes = result + " " + word;
            divide2(s, start + word.length(), end, curRes, wordDict, res);
        }
    }

    private boolean startWith2(String s, int start, int end, String word) {
        if (end - start + 1 < word.length()) {
            return false;
        }

        for (int i = 0; i < word.length(); i++) {
            if (s.charAt(start + i) != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

