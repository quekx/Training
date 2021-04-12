package com.qkx.example.solutions;

import java.util.Stack;

/**
 * @author kaixin
 * @since 2021-04-12 下午8:41
 * 输入一个字符串，其中包含 [](){} 六种括号，请你判断这个字符串组成的括号是否合法。
 * ([)]
 */
public class ValidParentheses {

    public static void main(String[] args) {
        String s = "()()()()()()(){{[][][[]]}}";
        System.out.println(new ValidParentheses().isValid(s));
    }

    public boolean isValid(String s) {
        if (s == null || s.length() <= 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                Character pre = stack.peek();
                if (pre == null || pre != '(') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if (c == ']') {
                Character pre = stack.peek();
                if (pre == null || pre != '[') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if (c == '}') {
                Character pre = stack.peek();
                if (pre == null || pre != '{') {
                    return false;
                } else {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }
        return true;
    }
}
