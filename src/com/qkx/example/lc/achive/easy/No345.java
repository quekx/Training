package com.qkx.example.lc.achive.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by qkx on 17/6/23.
 */
public class No345 {
    public String reverseVowels(String s) {
        if (s == null || s.length() <= 1) return s;

        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('A');
        set.add('e');
        set.add('E');
        set.add('i');
        set.add('I');
        set.add('o');
        set.add('O');
        set.add('u');
        set.add('U');

        int len = s.length();
        char[] chars = s.toCharArray();
        int left = 0;
        while (left <= len - 1 && !set.contains(chars[left])) {
            left++;
        }
        int right = len - 1;
        while (right >= 0 && !set.contains(chars[right])) {
            right--;
        }

        while (left < right) {
            char c = chars[left];
            chars[left] = chars[right];
            chars[right] = c;

            left++;
            while (left <= len - 1 && !set.contains(chars[left])) {
                left++;
            }
            right--;
            while (right >= 0 && !set.contains(chars[right])) {
                right--;
            }
        }
        return new String(chars);
    }
}
