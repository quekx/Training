package com.qkx.example.solutions.LeetCode.unfinish;

import java.util.*;

/**
 * Created by qkx on 16/9/28.
 */
public class No127 {
    public static int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        if (checkWordAdjacent(beginWord, endWord)) return 2;

        wordList.remove(beginWord);
        wordList.remove(endWord);

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        Queue<String> tempQueue = new LinkedList<>();

        int len = 2;
        while (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                String cur = queue.remove();
                if (checkWordAdjacent(cur, endWord)) return len;

                Set<String> temp = new HashSet<>();
                for (String next : wordList) {
                    if (checkWordAdjacent(next, cur)) {
                        temp.add(next);
                    }
                }
                for (String next : temp) {
                    wordList.remove(next);
                    tempQueue.add(next);
                }

            }

            len++;

            Queue<String> t = tempQueue;
            tempQueue = queue;
            queue = t;
        }


        return 0;
    }

    public static int ladderLength44(String beginWord, String endWord, Set<String> wordList) {
        if (checkWordAdjacent(beginWord, endWord)) return 2;

        wordList.add(beginWord);
        wordList.remove(wordList);

        Map<String, Set<String>> ref = new HashMap<>();
        for (String word : wordList) {
            Set<String> set = ref.get(word);
            if (set == null) {
                set = new HashSet<>();
                ref.put(word, set);
            }
            for (String other : wordList) {
                if (checkWordAdjacent(word, other)) {
                    set.add(other);
                }
            }
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        Queue<String> tempQueue = new LinkedList<>();

        Set<String> filter = new HashSet<>();

        int len = 2;
        while (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                String word = queue.remove();
                if (checkWordAdjacent(word, endWord)) return len;

                filter.add(word);
                for (String next : ref.get(word)) {
                    if (!filter.contains(next)) {
                        tempQueue.add(next);
                        filter.add(next);
                    }
                }
            }
            len++;

            Queue<String> t = queue;
            queue = tempQueue;
            tempQueue = t;
        }

        return 0;
    }


    public static boolean checkWordAdjacent(String q, String p) {
        if (q == null || p == null) return false;

        if (q.length() != p.length()) return false;

        int diff = 0;
        for (int i = 0; i < q.length(); i++) {
            if (q.charAt(i) != p.charAt(i)) diff++;
        }

        return diff == 1;
    }

    public static int ladderLength33(String beginWord, String endWord, Set<String> wordList) {
        if (checkWordAdjacent(beginWord, endWord)) return 2;

//        wordList.remove(beginWord);
        wordList.add(beginWord);
        wordList.remove(wordList);

        Map<String, Set<String>> ref = new HashMap<>();
        for (String word : wordList) {
            Set<String> set = ref.get(word);
            if (set == null) {
                set = new HashSet<>();
                ref.put(word, set);
            }
            for (String other : wordList) {
                if (checkWordAdjacent(word, other)) {
                    set.add(other);
                }
            }
        }

        Set<String> used = new HashSet<>();

        int res = dfs(beginWord, endWord, used, ref);

        return res == Integer.MAX_VALUE ? 0 : res;
    }


    private static int dfs(String curWord, String endWord,
                           Set<String> used, Map<String, Set<String>> ref) {
        if (checkWordAdjacent(curWord, endWord)) {
            return 2;
        }

        int res = Integer.MAX_VALUE;
        Set<String> nextSet = ref.get(curWord);
        if (nextSet == null) return res;

        for (String nextWord : nextSet) {
            if (!used.contains(nextWord)) {
                used.add(nextWord);
                int nextLen = dfs(nextWord, endWord, used, ref);
                used.remove(nextWord);

                if (nextLen != Integer.MAX_VALUE) res = Math.min(res, nextLen + 1);
            }
        }

        return res;
    }

    public static int ladderLength22(String beginWord, String endWord, Set<String> wordList) {
        Set<String> used = new HashSet<>();
        used.add(beginWord);

        wordList.add(endWord);
        int res = dfs22(beginWord, endWord, wordList, used);

        return res == Integer.MAX_VALUE ? 0 : res;
    }

    private static int dfs22(String lastWord, String endWord, Set<String> words, Set<String> used) {
        int res = Integer.MAX_VALUE;
        for (String word : words) {
            if (!used.contains(word) && checkWordAdjacent(lastWord, word)) {
                if (word.equals(endWord)) {
                    return 2;
                } else {
                    used.add(word);
                    int len = dfs22(word, endWord, words, used);
                    used.remove(word);
                    if (len != Integer.MAX_VALUE) res = Math.min(res, len + 1);
                }
            }
        }
        return res;
    }

}
