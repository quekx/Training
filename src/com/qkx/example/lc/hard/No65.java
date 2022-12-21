package com.qkx.example.lc.hard;

//A valid number can be split up into these components (in order):
//
//
// A decimal number or an integer.
// (Optional) An 'e' or 'E', followed by an integer.
//
//
// A decimal number can be split up into these components (in order):
//
//
// (Optional) A sign character (either '+' or '-').
// One of the following formats:
//
// One or more digits, followed by a dot '.'.
// One or more digits, followed by a dot '.', followed by one or more digits.
// A dot '.', followed by one or more digits.
//
//
//
// An integer can be split up into these components (in order):
//
//
// (Optional) A sign character (either '+' or '-').
// One or more digits.
//
//
// For example, all the following are valid numbers: ["2", "0089", "-0.1", "+3.1
//4", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"],
//while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "-
//-6", "-+3", "95a54e53"].
//
// Given a string s, return true if s is a valid number.
//
//
// Example 1:
//
//
//Input: s = "0"
//Output: true
//
//
// Example 2:
//
//
//Input: s = "e"
//Output: false
//
//
// Example 3:
//
//
//Input: s = "."
//Output: false
//
//
//
// Constraints:
//
//
// 1 <= s.length <= 20
// s consists of only English letters (both uppercase and lowercase), digits (0-
//9), plus '+', minus '-', or dot '.'.
//
//
// Related Topics String 👍 818 👎 1411


//leetcode submit region begin(Prohibit modification and deletion)
class No65 {
    /**
     * 小数
     * 1. 可选，一个符号位
     * 2. 一个或多个数字，后接小数点
     * 3. 一个或多个数字，后接小数点，后接一个或多个数字
     * 4. 一个小数点，后接一个或多个数字
     * <p>
     * 整点
     * 1. 可选，一个符号位
     * 2. 一个或多个数字
     */
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了53.04% 的Java用户
     * 	内存消耗:42.5 MB,击败了79.56% 的Java用户
     */
    public boolean isNumber(String s) {
        int ep = 0, len = s.length();
        while (ep < len && (s.charAt(ep) != 'e' && s.charAt(ep) != 'E')) {
            ep++;
        }
        return ep < len
                ? isIntegerOrDecimal(s, 0, ep - 1) && isInteger(s, ep + 1, len - 1)
                : isIntegerOrDecimal(s, 0, len - 1);
    }

    private boolean isIntegerOrDecimal(String s, int l, int r) {
        return isInteger(s, l, r) || isDecimal(s, l, r);
    }

    private boolean isDecimal(String s, int l, int r) {
        if (l > r) {
            return false;
        }
        if (s.charAt(l) == '-' || s.charAt(l) == '+') {
            l++;
        }
        if (l > r) {
            return false;
        }
        boolean hasPrefixNum = false;
        while (l <= r && (s.charAt(l) >= '0' && s.charAt(l) <= '9')) {
            hasPrefixNum = true;
            l++;
        }
        if (s.charAt(l) == '.') {
            l++;
        }
        if (!hasPrefixNum && l > r) {
            return false;
        }
        while (l <= r && (s.charAt(l) >= '0' && s.charAt(l) <= '9')) {
            l++;
        }
        return l > r;
    }

    private boolean isInteger(String s, int l, int r) {
        if (l > r) {
            return false;
        }
        if (s.charAt(l) == '-' || s.charAt(l) == '+') {
            l++;
        }
        if (l > r) {
            return false;
        }
        while (l <= r && (s.charAt(l) >= '0' && s.charAt(l) <= '9')) {
            l++;
        }
        return l > r;
    }

    public static void main(String[] args) {
        System.out.println(new No65().isNumber("2e0"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
