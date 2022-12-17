package com.qkx.example.lc.medium;//Evaluate the value of an arithmetic expression in Reverse Polish Notation.
//
// Valid operators are +, -, *, and /. Each operand may be an integer or
//another expression.
//
// Note that division between two integers should truncate toward zero.
//
// It is guaranteed that the given RPN expression is always valid. That means
//the expression would always evaluate to a result, and there will not be any
//division by zero operation.
//
//
// Example 1:
//
//
//Input: tokens = ["2","1","+","3","*"]
//Output: 9
//Explanation: ((2 + 1) * 3) = 9
//
//
// Example 2:
//
//
//Input: tokens = ["4","13","5","/","+"]
//Output: 6
//Explanation: (4 + (13 / 5)) = 6
//
//
// Example 3:
//
//
//Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
//Output: 22
//Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
//= ((10 * (6 / (12 * -11))) + 17) + 5
//= ((10 * (6 / -132)) + 17) + 5
//= ((10 * 0) + 17) + 5
//= (0 + 17) + 5
//= 17 + 5
//= 22
//
//
//
// Constraints:
//
//
// 1 <= tokens.length <= 10⁴
// tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the
//range [-200, 200].
//
// Related Topics Array Math Stack 👍 4869 👎 803


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class No150 {
    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了66.13% 的Java用户
     * 	内存消耗:44.9 MB,击败了10.87% 的Java用户
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int b = s.pop();
                int a = s.pop();
                s.push(doCal(a, b, token));
            } else {
                s.push(Integer.parseInt(token));
            }
        }
        return s.pop();
    }

    private int doCal(int a, int b, String opt) {
        if (opt.equals("+")) {
            return a + b;
        } else if (opt.equals("-")) {
            return a - b;
        } else if (opt.equals("*")) {
            return a * b;
        } else {
            return a / b;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
