package com.qkx.example.solutions.LeetCode.easy.No4x_5x;

/**
 * Created by qkx on 16/5/24.
 */
public class No58 {
    public static int lengthOfLastWord(String s) {

        s = s.trim();
        int i = s.length() - 1;
        int num = 0;

        while (i >= 0 && s.charAt(i) != ' ') {
            num++;
            i--;
        }

        return num;
    }
}
