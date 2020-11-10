package com.qkx.example.solutions.LeetCode.medium;

//Given a non-negative integer num represented as a string, remove k digits from
// the number so that the new number is the smallest possible.
//
//
// Note:
//
// The length of num is less than 10002 and will be ≥ k.
// The given num does not contain any leading zero.
//
//
//
//
// Example 1:
//
//Input: num = "1432219", k = 3
//Output: "1219"
//Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 w
//hich is the smallest.
//
//
//
// Example 2:
//
//Input: num = "10200", k = 1
//Output: "200"
//Explanation: Remove the leading 1 and the number is 200. Note that the output
//must not contain leading zeroes.
//
//
//
// Example 3:
//
//Input: num = "10", k = 2
//Output: "0"
//Explanation: Remove all the digits from the number and it is left with nothing
// which is 0.
//
// Related Topics Stack Greedy
// 👍 2780 👎 119


//leetcode submit region begin(Prohibit modification and deletion)
public class No402 {
    public static String removeKdigits(String num, int k) {
        if (num.length() <= k) {
            return "0";
        }

        char[] chars = num.toCharArray();
        StringBuilder builder = new StringBuilder();
        remove(builder, chars, 0, k);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < builder.length(); i++) {
            if (builder.charAt(i) != '0') {
                result.append(builder.subSequence(i, builder.length()));
                break;
            }
        }
        return result.length() != 0 ? result.toString() : "0";
    }


    // 移除策略：高k+1位中的最小数min，移除x位后n要到最高位
    private static void remove(StringBuilder builder, char[] chars, int start, int k) {

        // [start, len - 1]
        // l = len - 1 - start + 1 = len - start
        if (k >= chars.length - start) {
            return;
        }

        if (start > chars.length - 1) {
            return;
        }

        System.out.println("start: " + start + ", k: " + k + ", len: " + chars.length);
        // 最小数字符
        char min = chars[start];
        // 最小字符要移除前面字符的步数
        int index = start;
        for (int i = start; i <= start + k && i < chars.length; i++) {
            if (chars[i] == '0') {
                builder.append('0');
                remove(builder, chars, i + 1, k - (i - start));
                return;
            } else if (chars[i] < min) {
                min = chars[i];
                index = i;
            }
        }

        builder.append(min);
        remove(builder, chars, index + 1,k - (index - start));
    }


}
//leetcode submit region end(Prohibit modification and deletion)
