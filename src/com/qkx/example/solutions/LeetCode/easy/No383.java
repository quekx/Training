package com.qkx.example.solutions.LeetCode.easy;

/**
 * Created by qkx on 17/7/9.
 */
public class No383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null) return false;
        if (magazine == null) return false;

        int[] nums = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            nums[magazine.charAt(i) - 'a']++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            int index = ransomNote.charAt(i) - 'a';
            nums[index]--;
            if (nums[index] < 0) return false;
        }

        return true;
    }
}
