package com.qkx.example.utils;

/**
 * Created by qkx on 16/11/24.
 */
public class CharUtil {
    public static char[][] getChars (String[] strings) {
        int m = strings.length;
        int n = strings[0].length();

        char[][] res = new char[m][n];
        for (int i = 0; i < strings.length; i++) {
            res[i] = strings[i].toCharArray();
        }
        return res;
    }
}
