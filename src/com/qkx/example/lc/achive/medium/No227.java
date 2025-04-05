package com.qkx.example.lc.achive.medium;

import java.util.Stack;

/**
 * @author kaixin
 * @since 2021-10-29 11:44
 */
//Given a string s which represents an expression, evaluate this expression and
//return its value.
//
// The integer division should truncate toward zero.
//
// You may assume that the given expression is always valid. All intermediate
//results will be in the range of [-2³¹, 2³¹ - 1].
//
// Note: You are not allowed to use any built-in function which evaluates
//strings as mathematical expressions, such as eval().
//
//
// Example 1:
// Input: s = "3+2*2" 2*2+3
//Output: 7
// Example 2:
// Input: s = " 3/2 "
//Output: 1
// Example 3:
// Input: s = " 3+5 / 2 "
//Output: 5
//
//
// Constraints:
//
//
// 1 <= s.length <= 3 * 10⁵
// s consists of integers and operators ('+', '-', '*', '/') separated by some
//number of spaces.
// s represents a valid expression.
// All the integers in the expression are non-negative integers in the range [0,
// 2³¹ - 1].
// The answer is guaranteed to fit in a 32-bit integer.
//
// Related Topics Math String Stack 👍 3054 👎 445


//leetcode submit region begin(Prohibit modification and deletion)
public class No227 {

    public static void main(String[] args) {
        String s = "";
        s = " 3+5 / 2 ";
        s = " 3+2 * 2";
        System.out.println(new No227().calculate(s));
    }

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int num = 0;
        char signal = '+';
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean isDigit = c >= '0' && c <= '9';
            if (isDigit) {
                num = num * 10 + (c - '0');
            }
            if ((!isDigit && c != ' ') || i == s.length() - 1) {
                switch (signal) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                signal = c;
                num = 0;
            }
        }
        int res = 0;
        for (Integer ele : stack) {
            res += ele;
        }
        return res;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
//leetcode submit region end(Prohibit modification and deletion)
