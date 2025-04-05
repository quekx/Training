package com.qkx.example.lc.achive.hard;

import java.util.*;

/**
 * @author kaixin
 * @since 2021-01-13 17:31
 */
//You are given a string s and an array of strings words of the same length. Ret
//urn all starting indices of substring(s) in s that is a concatenation of each wo
//rd in words exactly once, in any order, and without any intervening characters.
//
//
// You can return the answer in any order.
//
//
// Example 1:
//
//
//Input: s = "barfoothefoobarman", words = ["foo","bar"]
//Output: [0,9]
//Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" re
//spectively.
//The output order does not matter, returning [9,0] is fine too.
//
//
// Example 2:
//
//
//Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//Output: []
//
//
// Example 3:
//
//
//Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//Output: [6,9,12]
//
//
//
// Constraints:
//
//
// 1 <= s.length <= 104
// s consists of lower-case English letters.
// 1 <= words.length <= 5000
// 1 <= words[i].length <= 30
// words[i] consists of lower-case English letters.
//
// Related Topics Hash Table Two Pointers String
// 👍 1117 👎 1388


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 把 s 切分分组
 * s[0, len-1], s[len, 2len-1], ... s[i*len, (i+1)*len-1]
 * s[1, len], s[len+1, 2len], ...
 * s[2, len+1], s[len+2, 2len+1], ...
 * ... ...
 * s[len-1, 2len-2], s[2len-1, 3len-2], ...
 */
public class No30 {
    public static void main(String[] args) {
//        String s = "12343212433124";barfoothefoobarman
        String s = "barfoofoobarthefoobarman";
        s = "wordgoodgoodgoodbestword";
        System.out.println(s.substring(1, 4));

//        String[] word = {"1", "2", "3", "4"};
        String[] word = {"bar", "foo", "the"};
        word = (String[]) Arrays.asList("word", "good", "best", "good").toArray();
        System.out.println(new No30().findSubstring(s, word));
    }

    /**
     * 解答成功:
     * 执行耗时:222 ms,击败了25.72% 的Java用户
     * 内存消耗:39.7 MB,击败了48.92% 的Java用户
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new LinkedList<>();
        if (s == null || s.isEmpty() || words == null || words.length == 0) {
            return result;
        }

        // 词计数
        Map<String, Integer> wordMap = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (String word : words) {
            addCount(wordMap, word);

            set.add(word);
        }

        int len = words[0].length();
        for (int delta = 0; delta <= len - 1; delta++) {
            // s[i * len + delta, (i + 1) * len - 1 + delta]
            int startIndex = 0;
            int nextIndex = 0;
            Map<String, Integer> stepMap = new HashMap<>(wordMap);
            Queue<String> queue = new LinkedList<>();
            while (nextIndex * len + delta >= 0
                    && (nextIndex + 1) * len - 1 + delta <= s.length() - 1) {
                String next = s.substring(nextIndex * len + delta, (nextIndex + 1) * len + delta);
                if (!set.contains(next)) {
                    nextIndex++;
                    startIndex = nextIndex;
                    stepMap = new HashMap<>(wordMap);
                    queue.clear();
                    continue;
                }

                Integer nextWordCount = stepMap.get(next);
                System.out.println("---next: " + next + "---queue:" + queue + "---map:" + stepMap);
                if (nextWordCount == null) {
                    while (queue.peek() != null && !queue.peek().equals(next)) {
                        String w = queue.poll();
                        startIndex++;
                        addCount(stepMap, w);
                    }
                    queue.poll();
                    startIndex++;
                    queue.add(next);
                    nextIndex++;
                } else if (nextWordCount == 1) {
                    stepMap.remove(next);
                    if (stepMap.isEmpty()) {
                        result.add(startIndex * len + delta);
                        String startWord = queue.poll();
                        startIndex++;
                        stepMap.put(startWord, 1);
                    }
                    queue.add(next);
                    nextIndex++;
                } else {
                    stepMap.put(next, nextWordCount - 1);
                    queue.add(next);
                    nextIndex++;
                }
            }
        }

        return result;
    }

    private void addCount(Map<String, Integer> map, String word) {
        Integer num = map.getOrDefault(word, 0);
        map.put(word, num + 1);
    }

    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> result = new LinkedList<>();
        if (s == null || s.isEmpty() || words == null || words.length == 0) {
            return result;
        }

        Set<String> set = new HashSet<>(Arrays.asList(words));
        int num = words.length;
        int len = words[0].length();
        for (int delta = 0; delta <= len - 1; delta++) {
            // 记录上一个当前word的位置
            Map<String, Integer> posMap = new HashMap<>();
            int startIndex = 0;
            for (int i = 0; (i + 1) * len - 1 + delta <= s.length() - 1; i++) {
                int start = i * len + delta;
                int end = (i + 1) * len - 1 + 1 + delta;
                String wordI = s.substring(start, end);
                if (!set.contains(wordI)) {
                    posMap.clear();
                    startIndex = i + 1;
                    continue;
                }
                System.out.println(start + "---" + end + "---" + delta + "---" + posMap);
                System.out.println(wordI + "---i:" + i + "---startIndex:" + startIndex + "---num:" + num);
                Integer pos = posMap.get(wordI);
                if (pos == null || i - pos >= num) {
                    // 说明从 startIndex 到 i 之间没有重复
                    // startIndex ->
                    if (i - startIndex == num - 1) {
                        result.add(startIndex * len + delta);
                        startIndex++;
                    }
                } else {
                    // 从 startIndex 到 i 之间有重复
                    // 从 startIndex + 1 开始算
                    startIndex = pos + 1;
                }
                posMap.put(wordI, i);
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

