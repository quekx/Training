package com.qkx.example.solutions.LeetCode.medium;

/**
 * @author kaixin
 * @since 2020-12-14 19:59
 */
//Given two non-negative integers num1 and num2 represented as strings, return t
//he product of num1 and num2, also represented as a string.
//
// Note: You must not use any built-in BigInteger library or convert the inputs
//to integer directly.
//
//
// Example 1:
// Input: num1 = "2", num2 = "3"
//Output: "6"
// Example 2:
// Input: num1 = "123", num2 = "456"
//Output: "56088"
//
//
// Constraints:
//
//
// 1 <= num1.length, num2.length <= 200
// num1 and num2 consist of digits only.
// Both num1 and num2 do not contain any leading zero, except the number 0 itsel
//f.
//
// Related Topics Math String
// 👍 2096 👎 895


//leetcode submit region begin(Prohibit modification and deletion)
public class No43 {

    public static void main(String[] args) {
        String num1 = "923";
        String num2 = "956";
        System.out.println(new No43().multiply(num1, num2));
    }

    /**
     * result 第 k 位( len1+len2-1 >= k >= 0)计算
     * <p>
     * num[i] * num[k - i]
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        int len1 = num1.length();
        int len2 = num2.length();
        int carry = 0;
        for (int k = 0; k <= len1 + len2 - 2; k++) {
            int temp = carry;

            for (int i = 0; i < num1.length(); i++) {
                if (k - i < num2.length() && k - i >= 0) {
                    int a = convertCharToInt(num1.charAt(num1.length() - 1 - i));
                    int b = convertCharToInt(num2.charAt(num2.length() - 1 - k + i));
                    temp += a * b;
                }
            }
            carry = temp / 10;
            sb.append(temp % 10);
        }
        if (carry != 0) {
            sb.append(carry);
        }

        sb.reverse();
        return sb.toString();
    }

    private int convertCharToInt(char c) {
        return c - '0';
    }

    private char convertIntToChar(int i) {
        return (char) ('0' + i);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

