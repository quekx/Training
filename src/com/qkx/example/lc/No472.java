package com.qkx.example.lc;

//Given an array of strings words (without duplicates), return all the
//concatenated words in the given list of words.
//
// A concatenated word is defined as a string that is comprised entirely of at
//least two shorter words (not necesssarily distinct) in the given array.
//
//
// Example 1:
//
//
//Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog",
//"hippopotamuses","rat","ratcatdogcat"]
//Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
//Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
//"dogcatsdog" can be concatenated by "dog", "cats" and "dog";
//"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
//
// Example 2:
//
//
//Input: words = ["cat","dog","catdog"]
//Output: ["catdog"]
//
//
//
// Constraints:
//
//
// 1 <= words.length <= 10⁴
// 1 <= words[i].length <= 30
// words[i] consists of only lowercase English letters.
// All the strings of words are unique.
// 1 <= sum(words[i].length) <= 10⁵
//
//
// Related Topics Array String Dynamic Programming Depth-First Search Trie 👍 35
//48 👎 272


import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Time Limit Exceeded
 */

/**
 * 解答成功:
 * 	执行耗时:48 ms,击败了94.89% 的Java用户
 * 	内存消耗:52.5 MB,击败了53.95% 的Java用户
 */
//leetcode submit region begin(Prohibit modification and deletion)
class No472 {

    private Trie root = new Trie();

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        List<String> ans = new LinkedList<>();
        for (String word : words) {
            boolean[] visit = new boolean[word.length()];
            if (query(word, 0, root, visit)) {
                ans.add(word);
            } else {
                addWord(word);
            }
        }
        return ans;
    }

    private boolean query(String word, int start, Trie cur, boolean[] visit) {
        if (word.length() == start) {
            return true;
        }
        if (visit[start]) {
            return false;
        }
        visit[start] = true;
        for (int i = start; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (cur.children[idx] == null) {
                return false;
            }
            cur = cur.children[idx];
            if (cur.isEnd && query(word, i + 1, root, visit)) {
                return true;
            }
        }
        return false;
    }



    private void addWord(String word) {
        Trie cur = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (cur.children[idx] == null) {
                cur.children[idx] = new Trie();
            }
            cur = cur.children[idx];
        }
        cur.isEnd = true;
    }

    class Trie {
        Trie[] children = new Trie[26];
        boolean isEnd;
    }

    public static void main(String[] args) {
//        String[] words = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        String[] words = {"a", "b", "ab"};
        System.out.println(new No472().findAllConcatenatedWordsInADict(words));
        System.out.println(123);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

