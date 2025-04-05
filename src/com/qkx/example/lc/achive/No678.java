package com.qkx.example.lc.achive;

//Given a string s containing only three types of characters: '(', ')' and '*',
//return true if s is valid.
//
// The following rules define a valid string:
//
//
// Any left parenthesis '(' must have a corresponding right parenthesis ')'.
// Any right parenthesis ')' must have a corresponding left parenthesis '('.
// Left parenthesis '(' must go before the corresponding right parenthesis ')'.
//
// '*' could be treated as a single right parenthesis ')' or a single left
//parenthesis '(' or an empty string "".
//
//
//
// Example 1:
// Input: s = "()"
//Output: true
//
// Example 2:
// Input: s = "(*)"
//Output: true
//
// Example 3:
// Input: s = "(*))"
//Output: true
//
//
// Constraints:
//
//
// 1 <= s.length <= 100
// s[i] is '(', ')' or '*'.
//
//
// Related Topics String Dynamic Programming Stack Greedy 👍 4302 👎 106


//leetcode submit region begin(Prohibit modification and deletion)
class No678 {

    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了5.84% 的Java用户
     * 	内存消耗:40.6 MB,击败了34.63% 的Java用户
     */
    public boolean checkValidString(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = s.charAt(i) == '*';
        }
        for (int i = 0; i <= n - 2; i++) {
            char ch1 = s.charAt(i), ch2 = s.charAt(i + 1);
            if ((ch1 == '(' || ch1 == '*') && (ch2 == ')' || ch2 == '*')) {
                dp[i][i + 1] = true;
            }
        }
        for (int d = 2; d <= n - 1; d++) {
            for (int i = 0; i + d <= n - 1; i++) {
                // dp[i][i + d]
                char ch1 = s.charAt(i), ch2 = s.charAt(i + d);
                if ((ch1 == '(' || ch1 == '*') && (ch2 == ')' || ch2 == '*') && dp[i + 1][i + d - 1]) {
                    dp[i][i + d] = true;
                    continue;
                }
                for (int k = i; k < i + d; k++) {
                    if (dp[i][k] && dp[k + 1][i + d]) {
                        dp[i][i + d] = true;
                        break;
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new No678().checkValidString("((((()(()()()*()(((((*)()*(**(())))))(())()())(((())())())))))))(((((())*)))()))(()((*()*(*)))(*)()"));
    }


    /**
     * Time Limit Exceeded
     */
    public boolean checkValidString2(String s) {
        return dfs2(s, 0, 0);
    }

    private boolean dfs2(String s, int i, int count) {
        if (i == s.length()) {
            return count == 0;
        }
        char ch = s.charAt(i);
        if (ch == '(') {
            return dfs2(s, i + 1, count + 1);
        } else if (ch == ')') {
            if (count == 0) {
                return false;
            }
            return dfs2(s, i + 1, count - 1);
        } else {
            if (dfs2(s, i + 1, count)) {
                return true;
            }
            if (dfs2(s, i + 1, count + 1)) {
                return true;
            }
            return count > 0 && dfs2(s, i + 1, count - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

