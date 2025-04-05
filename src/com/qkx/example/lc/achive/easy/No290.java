package com.qkx.example.lc.achive.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by qkx on 17/5/25.
 */
public class No290 {
    /**
     * Given a pattern and a string str, find if str follows the same pattern.
     * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
     * Examples:
     * pattern = "abba", str = "dog cat cat dog" should return true.
     * pattern = "abba", str = "dog cat cat fish" should return false.
     * pattern = "aaaa", str = "dog cat cat dog" should return false.
     * pattern = "abba", str = "dog dog dog dog" should return false.
     */
    public boolean wordPattern(String pattern, String str) {
        if (pattern == null || str == null) return false;

        String[] words = str.split(" ");
        if (pattern.length() != words.length) return false;

        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = map.get(c);
            if (word == null) {
                if (set.contains(words[i])) return false;
                map.put(c, words[i]);
                set.add(words[i]);
            } else if (!word.equals(words[i])) {
                return false;
            }
        }
        return true;
    }
}
