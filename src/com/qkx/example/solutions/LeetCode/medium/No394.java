package com.qkx.example.solutions.LeetCode.medium;//Given an encoded string, return its decoded string.
//
// The encoding rule is: k[encoded_string], where the encoded_string inside the
//square brackets is being repeated exactly k times. Note that k is guaranteed to
//be a positive integer.
//
// You may assume that the input string is always valid; No extra white spaces,
//square brackets are well-formed, etc.
//
// Furthermore, you may assume that the original data does not contain any digit
//s and that digits are only for those repeat numbers, k. For example, there won't
// be input like 3a or 2[4].
//
//
// Example 1:
// Input: s = "3[a]2[bc]"
//Output: "aaabcbc"
// Example 2:
// Input: s = "3[a2[c]]"
//Output: "accaccacc"
// Example 3:
// Input: s = "2[abc]3[cd]ef"
//Output: "abcabccdcdcdef"
// Example 4:
// Input: s = "abc3[cd]xyz"
//Output: "abccdcdcdxyz"
// Related Topics Stack Depth-first Search
// 👍 3900 👎 192


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
public class No394 {

    public static void main(String[] args) {
//        String str = "3[a]2[bc]";
//        String str = "3[a2[c]]";
//        String str = "2[abc]3[cd]ef";
        String str = "10[a]";
        System.out.println(decodeString(str));
    }

    public static String decodeString(String s) {
        if (s == null) {
            return null;
        }

        Stack<Node> stack = new Stack<>();

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                count = count * 10 + c - '0';
                stack.push(new Node(false, sb.toString(), 0));
                sb.delete(0, sb.length());
            } else if (c == '[') {
                stack.push(new Node(true, "", count));
                count = 0;
            } else if (c == ']') {
                stack.push(new Node(false, sb.toString(), 0));
                sb.delete(0, sb.length());

                String str = "";
                while (!stack.isEmpty()) {
                    Node node = stack.pop();
                    if (!node.isNum) {
                        str = node.str + str;
                    } else {
                        StringBuilder temp = new StringBuilder();
                        for (int k = 1; k <= node.count; k++) {
                            temp.append(str);
                        }
                        stack.push(new Node(false, temp.toString(), 0));
                        break;
                    }
                }
            } else {
                sb.append(c);
            }
        }
        stack.push(new Node(false, sb.toString(), 0));

        String result = "";
        while (!stack.isEmpty()) {
            result = stack.pop().str + result;
        }
        return result;
    }

    static class Node {
        boolean isNum;
        String str;
        int count;

        public Node(boolean isNum, String str, int count) {
            this.isNum = isNum;
            this.str = str;
            this.count = count;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
