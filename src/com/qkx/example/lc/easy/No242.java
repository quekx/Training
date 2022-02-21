package com.qkx.example.lc.easy;

/**
 * Created by qkx on 17/4/30.
 */
public class No242 {
    public static boolean isAnagram(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;

        int[] temp = new int[26];
        for (int i = 0; i < s.length(); i++) {
            temp[s.charAt(i) - 'a']++;
            temp[t.charAt(i) - 'a']--;
        }
        for (int i : temp) {
            if (i != 0) return false;
        }
        return true;
    }
}
