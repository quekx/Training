package com.qkx.example.lc;

//Solve a given equation and return the value of 'x' in the form of a string "x=
//#value". The equation contains only '+', '-' operation, the variable 'x' and
//its coefficient. You should return "No solution" if there is no solution for the
//equation, or "Infinite solutions" if there are infinite solutions for the
//equation.
//
// If there is exactly one solution for the equation, we ensure that the value
//of 'x' is an integer.
//
//
// Example 1:
//
//
//Input: equation = "x+5-3+x=6+x-2"
//Output: "x=2"
//
//
// Example 2:
//
//
//Input: equation = "x=x"
//Output: "Infinite solutions"
//
//
// Example 3:
//
//
//Input: equation = "2x=x"
//Output: "x=0"
//
//
//
// Constraints:
//
//
// 3 <= equation.length <= 1000
// equation has exactly one '='.
// equation consists of integers with an absolute value in the range [0, 100]
//without any leading zeros, and the variable 'x'.
//
//
// Related Topics Math String Simulation 👍 433 👎 778


import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class No640 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了79.29% 的Java用户
     * 	内存消耗:40.6 MB,击败了65.68% 的Java用户
     */
    public String solveEquation(String equation) {
        int leftTag = 1, sign = 1;
        int m = 0;
        Deque<Integer> deque = new LinkedList<>();
        int num = 0;
        for (int i = 0; i < equation.length(); i++) {
            char ch = equation.charAt(i);
            if (ch == 'x') {
                if (i - 1 < 0 || equation.charAt(i - 1) < '0' || equation.charAt(i - 1) > '9') {
                    num = 1;
                }
                m += num * -leftTag * sign;
                num = 0;
            } else if (ch == '-') {
                deque.push(num * leftTag * sign);
                num = 0;
                sign = -1;
            } else if (ch == '+') {
                deque.push(num * leftTag * sign);
                num = 0;
                sign = 1;
            } else if (ch == '=') {
                deque.push(num * leftTag * sign);
                leftTag = -1;
                num = 0;
                sign = 1;
            } else {
                num = num * 10 + ch - '0';
            }
        }
        System.out.println(deque);
        System.out.println(m);
        deque.push(num * leftTag * sign);
        int sum = 0;
        for (int n : deque) {
            sum += n;
        }
        if (m == 0) {
            return sum == 0 ? "Infinite solutions" : "No solution";
        }
        return "x=" + sum / m;
    }

    public static void main(String[] args) {
        String e = "0x=0";
//        String e = "x+5-3+x=6+x-2";
        System.out.println(new No640().solveEquation(e));
        // x = 0; 1x = 0; 0x = 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

