package com.qkx.example.lc.achive;

//Given a string s and a string array dictionary, return the longest string in
//the dictionary that can be formed by deleting some of the given string
//characters. If there is more than one possible result, return the longest word with the
//smallest lexicographical order. If there is no possible result, return the empty
//string.
//
//
// Example 1:
//
//
//Input: s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
//Output: "apple"
//
//
// Example 2:
//
//
//Input: s = "abpcplea", dictionary = ["a","b","c"]
//Output: "a"
//
//
//
// Constraints:
//
//
// 1 <= s.length <= 1000
// 1 <= dictionary.length <= 1000
// 1 <= dictionary[i].length <= 1000
// s and dictionary[i] consist of lowercase English letters.
//
//
// Related Topics Array Two Pointers String Sorting 👍 1595 👎 351


import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 解答成功:
     * 	执行耗时:24 ms,击败了61.73% 的Java用户
     * 	内存消耗:43.4 MB,击败了46.15% 的Java用户
     */
    public String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((a, b) -> {
            if (a.length() != b.length()) {
                return b.length() - a.length();
            } else {
                return a.compareTo(b);
            }
        });
        for (String word : dictionary) {
            if (check(s, word)) {
                return word;
            }
        }
        return "";
    }

    private boolean check(String s, String word) {
        int i = 0, j = 0;
        while (i < s.length() && j < word.length()) {
            if (s.charAt(i) == word.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
            }
        }
        return j == word.length();
    }

    public String findLongestWord2(String s, List<String> dictionary) {
        Trie root = new Trie();
        for (String word : dictionary) {
            build(root, word);
        }
        dfs(s, 0, root);
        return ans;
    }

    String ans = "";

    private void dfs(String s, int i, Trie cur) {
        if (i == s.length()) {
            if (cur.isEnd) {
                if (cur.word.length() > ans.length()) {
                    ans = cur.word;
                } else if (cur.word.length() == ans.length() && cur.word.compareTo(ans) < 0) {
                    ans = cur.word;
                }
            }
            return;
        }
        int idx = s.charAt(i) - 'a';
        if (cur.children[idx] != null) {
            dfs(s, i + 1, cur.children[idx]);
        }
        dfs(s, i + 1, cur);
    }

    private void build(Trie root, String word) {
        Trie cur = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (cur.children[idx] == null) {
                cur.children[idx] = new Trie();
            }
            cur = cur.children[idx];
        }
        cur.isEnd = true;
        cur.word = word;
    }

    class Trie {
        Trie[] children;
        boolean isEnd;
        String word;

        public Trie() {
            children = new Trie[26];
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

