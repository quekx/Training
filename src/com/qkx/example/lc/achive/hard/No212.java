package com.qkx.example.lc.achive.hard;

import java.util.LinkedList;
import java.util.List;

/**
 * @author kaixin
 * @since 2020-12-09 18:59
 */
//Given an m x n board of characters and a list of strings words, return all wor
//ds on the board.
//
// Each word must be constructed from letters of sequentially adjacent cells, wh
//ere adjacent cells are horizontally or vertically neighboring. The same letter c
//ell may not be used more than once in a word.
//
//
// Example 1:
//
//
//Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f"
//,"l","v"]], words = ["oath","pea","eat","rain"]
//Output: ["eat","oath"]
//
//
// Example 2:
//
//
//Input: board = [["a","b"],["c","d"]], words = ["abcb"]
//Output: []
//
//
//
// Constraints:
//
//
// m == board.length
// n == board[i].length
// 1 <= m, n <= 12
// board[i][j] is a lowercase English letter.
// 1 <= words.length <= 3 * 104
// 1 <= words[i].length <= 10
// words[i] consists of lowercase English letters.
// All the strings of words are unique.
//
// Related Topics Backtracking Trie
// 👍 3183 👎 133


//leetcode submit region begin(Prohibit modification and deletion)
public class No212 {
    public static void main(String[] args) {
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};
        System.out.println(new No212().findWords(board, words));
    }

    /**
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:37.5 MB,击败了75.32% 的Java用户
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> list = new LinkedList<>();
        boolean[][] used = new boolean[board.length][board[0].length];
        for (String word : words) {
            if (find(board, word, used)) {
                list.add(word);
            }
        }
        return list;
    }

    private boolean find(char[][] board, String word, boolean[][] used) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (find(board, i, j, word, 0, used)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean find(char[][] board, int i, int j,
                         String word, int index,
                         boolean[][] used) {
        if (i < 0 || i > board.length - 1) {
            return false;
        }

        if (j < 0 || j > board[0].length - 1) {
            return false;
        }

        if (used[i][j]) {
            return false;
        }

        if (board[i][j] == word.charAt(index)) {
            if (index == word.length() - 1) {
                return true;
            }
            used[i][j] = true;
            if (find(board, i - 1, j, word, index + 1, used)
                    || find(board, i + 1, j, word, index + 1, used)
                    || find(board, i, j - 1, word, index + 1, used)
                    || find(board, i, j + 1, word, index + 1, used)) {
                used[i][j] = false;
                return true;
            }
            used[i][j] = false;
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
