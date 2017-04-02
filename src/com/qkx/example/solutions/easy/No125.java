package com.qkx.example.solutions.easy;

/**
 * Created by qkx on 16/9/20.
 */
public class No125 {
    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }

        if (s.length() == 0) {
            return true;
        }

        int len = s.length();
        int start = 0;
        while (start <= len - 1 && !isValid(s.charAt(start))) {
            start++;
        }
        int end = s.length() - 1;
        while (end >= 0 && !isValid(s.charAt(end))) {
            end--;
        }

        while (start < end) {
            char a = s.charAt(start);
            char b = s.charAt(end);
            if (equals(a, b)) {
                do {
                    start++;
                } while (start <= len - 1 && !isValid(s.charAt(start)));
                do {
                    end--;
                } while (end >= 0 && !isValid(s.charAt(end)));
            } else {
                return false;
            }
        }

        return true;
    }

    private boolean equals(char a, char b) {
        if (a == b) {
            return true;
        }
        if (isLetter(a) && isLetter(b)) {
            return Math.abs(a - b) == 32;
        }
        return false;
    }

    private boolean isValid(char c) {
        return isNum(c) || isLetter(c);
    }

    private boolean isNum(char c) {
        return (c >= '0' && c <= '9');
    }

    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z')
                || (c >= 'A' && c <= 'Z');
    }
}
