package com.qkx.example.solutions.LeetCode.medium;

/**
 * Created by qkx on 17/6/3.
 */
public class No318 {
    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) return 0;

        int len = words.length;
        int[] temp = new int[len];
        for (int i = 0; i < len; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                temp[i] |= 1 << (word.charAt(j) - 'a');
            }
        }

        int result = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i != j && temp[i] != 0 && temp[j] != 0 &&
                        ((temp[i] & temp[j]) == 0)) {
                    result = Math.max(result, words[i].length() * words[j].length());
                }
            }
        }

        return result;
    }
}
