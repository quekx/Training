package com.qkx.example.lc.achive.hard;

//Given a string num that contains only digits and an integer target, return
//all possibilities to insert the binary operators '+', '-', and/or '*' between the
//digits of num so that the resultant expression evaluates to the target value.
//
// Note that operands in the returned expressions should not contain leading
//zeros.
//
//
// Example 1:
//
//
//Input: num = "123", target = 6
//Output: ["1*2*3","1+2+3"]
//Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
//
//
// Example 2:
//
//
//Input: num = "232", target = 8
//Output: ["2*3+2","2+3*2"]
//Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
//
//
// Example 3:
//
//
//Input: num = "3456237490", target = 9191
//Output: []
//Explanation: There are no expressions that can be created from "3456237490"
//to evaluate to 9191.
//
//
//
// Constraints:
//
//
// 1 <= num.length <= 10
// num consists of only digits.
// -2³¹ <= target <= 2³¹ - 1
//
//
// Related Topics Math String Backtracking 👍 2810 👎 500


import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class No282 {

    /**
     * 把数据计算放在过程中，比最终遍历后分别计算每个字符式子块
     * 解答成功:
     * 	执行耗时:61 ms,击败了89.00% 的Java用户
     * 	内存消耗:43 MB,击败了89.49% 的Java用户
     */
    public List<String> addOperators(String num, int target) {
        List<String> ans = new LinkedList<>();
        StringBuilder tmp = new StringBuilder();
        long start = 0L;
        for (int i = 0; i < num.length(); i++) {
            start = start * 10 + num.charAt(i) - '0';
            tmp.append(num.charAt(i));
            f(num, target, i + 1, 0L, start, tmp, ans);
            if (start == 0) {
                break;
            }
        }
        return ans;
    }

    /**
     * @param num
     * @param target
     * @param i
     * @param preSum 上一个标点之间的结果
     * @param preVal 上一个标点到 s[i-1] 为止的值, 此值要单独作为参数是因为前一个值会和乘法结合
     * @param tmp
     * @param ans
     */
    private void f(String num, int target, int i, long preSum, long preVal, StringBuilder tmp, List<String> ans) {
        if (num.length() == i) {
            if (preSum + preVal == target) {
                ans.add(tmp.toString());
            }
            return;
        }

        int val = 0;
        int count = 0;
        tmp.append('+');
        for (int k = i; k < num.length(); k++) {
            // x = num.length() - i
            count++;
            val = val * 10 + num.charAt(k) - '0';
            tmp.append(num.charAt(k));
            f(num, target, k + 1, preSum + preVal, val, tmp, ans);
            if (val == 0) {
                break;
            }
        }
        /**
         * +1 f -1 +12 f -12
         */
        tmp.delete(tmp.length() - count, tmp.length());
        tmp.delete(tmp.length() - 1, tmp.length());

        val = 0;
        count = 0;
        tmp.append('-');
        for (int k = i; k < num.length(); k++) {
            count++;
            val = val * 10 + num.charAt(k) - '0';
            tmp.append(num.charAt(k));
            f(num, target, k + 1, preSum + preVal, -val, tmp, ans);
            if (val == 0) {
                break;
            }
        }
        tmp.delete(tmp.length() - count, tmp.length());
        tmp.delete(tmp.length() - 1, tmp.length());

        val = 0;
        count = 0;
        tmp.append('*');
        for (int k = i; k < num.length(); k++) {
            count++;
            val = val * 10 + num.charAt(k) - '0';
            tmp.append(num.charAt(k));
            f(num, target, k + 1, preSum, preVal * val, tmp, ans);
            if (val == 0) {
                break;
            }
        }
        tmp.delete(tmp.length() - count, tmp.length());
        tmp.delete(tmp.length() - 1, tmp.length());
    }


    /**
     * 解答成功:
     * 执行耗时:371 ms,击败了16.88% 的Java用户
     * 内存消耗:43.3 MB,击败了79.10% 的Java用户
     */
    public List<String> addOperators2(String num, int target) {
        List<String> ans = new LinkedList<>();
        char[] opts = {'+', '-', '*'};
        StringBuilder tmp = new StringBuilder();
        tmp.append(num.charAt(0));
        dfs(num, 1, tmp, ans, target, opts, num.charAt(0) == '0');
        return ans;
    }

    private void dfs(String num, int i, StringBuilder tmp, List<String> ans, int target, char[] opts, boolean startWithZero) {
        if (i == num.length()) {
            String s = tmp.toString();
            if (cal(s) == target) {
                ans.add(s);
            }
            return;
        }

        char c = num.charAt(i);
        for (char opt : opts) {
            tmp.append(opt);
            tmp.append(c);
            dfs(num, i + 1, tmp, ans, target, opts, c == '0');
            tmp.delete(tmp.length() - 2, tmp.length());
        }
        if (!startWithZero) {
            tmp.append(c);
            dfs(num, i + 1, tmp, ans, target, opts, false);
            tmp.delete(tmp.length() - 1, tmp.length());
        }
    }

    private long cal(String s) {
        Deque<Long> stack = new LinkedList<>();
        char sign = '+';
        long num = 0L;
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (isNum(c)) {
                num = num * 10 + c - '0';
            }
            if (!isNum(c) || i == s.length() - 1) {
                if (sign == '*') {
                    long a = stack.pop();
                    stack.push(doCal(sign, a, num));
                } else {
                    stack.push(sign == '-' ? -num : num);
                }
                num = 0L;
                sign = c;
            }
            i++;
        }

        long ans = 0L;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

    private long doCal(char opt, long aa, long bb) {
        if (opt == '*') {
            return aa * bb;
        } else if (opt == '+') {
            return aa + bb;
        } else {
            return aa - bb;
        }
    }

    private boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {
        String num = "105";
        int target = 5;
        System.out.println(new No282().addOperators(num, target));
//        System.out.println(new No282().cal("1+2+3+4"));
//        System.out.println(new No282().cal("1+2-3+4+5*6-7+8*9"));

        String x = "2147483648";
        int y = -2147483648;
        System.out.println(214748364 * 10 + 8);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

