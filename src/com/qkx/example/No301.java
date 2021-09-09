package com.qkx.example;
//Given a string s that contains parentheses and letters, remove the minimum
//number of invalid parentheses to make the input string valid.
//
// Return all the possible results. You may return the answer in any order.
//
//
// Example 1:
//
//
//Input: s = "()())()"
//Output: ["(())()","()()()"]
//
//
// Example 2:
//
//
//Input: s = "(a)())()"
//Output: ["(a())()","(a)()()"]
//
//
// Example 3:
//
//
//Input: s = ")("
//Output: [""]
//
//
//
// Constraints:
//
//
// 1 <= s.length <= 25
// s consists of lowercase English letters and parentheses '(' and ')'.
// There will be at most 20 parentheses in s.
//
// Related Topics String Backtracking Breadth-First Search 👍 3837 👎 183

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author kaixin
 * @since 2021-09-08 17:23
 */
public class No301 {
    /**
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new LinkedList<>();
        if (s == null || s.isEmpty()) {
            return result;
        }

        int[] weight = new int[s.length()];
        if (s.charAt(0) == '(') {
            weight[0] = 1;
        } else if (s.charAt(0) == ')') {
            weight[0] = -2;
        }

        int start = 0;
        for (int i = 1; i <= s.length() - 1; i++) {
            if (s.charAt(i) == '(') {
                weight[i] = weight[i - 1] >= 0 ? weight[i - 1] + 1 : 1;
            } else if (s.charAt(0) == ')') {
                weight[i] = weight[i - 1] >= 0 ? weight[i - 1] - 1 : -2;
                if (weight[i] == -2) {
                    start = i + 1;
                } else if (weight[i] == -1) {

                    start = i + 1;
                }
            } else {
                weight[i] = weight[i - 1] >= 0 ? weight[i - 1] : 0;
            }
        }



        return null;
    }

}
