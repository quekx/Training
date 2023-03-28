package com.qkx.example.lc;

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

    public boolean checkValidString(String s) {
        return dfs(s, 0, 0);
    }

    /**
     * Time Limit Exceeded
     */
    public boolean checkValidString2(String s) {
        return dfs(s, 0, 0);
    }

    private boolean dfs(String s, int i, int count) {
        if (i == s.length()) {
            return count == 0;
        }
        char ch = s.charAt(i);
        if (ch == '(') {
            return dfs(s, i + 1, count + 1);
        } else if (ch == ')') {
            if (count == 0) {
                return false;
            }
            return dfs(s, i + 1, count - 1);
        } else {
            if (dfs(s, i + 1, count)) {
                return true;
            }
            if (dfs(s, i + 1, count + 1)) {
                return true;
            }
            return count > 0 && dfs(s, i + 1, count - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

