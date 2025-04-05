package com.qkx.example.lc.lc2025;

// medium
public class No43_medium {

    /**
     * 通过
     * 311 / 311 个通过的测试用例
     * QueKaix
     * QueKaix
     * 提交于 2025.04.04 17:25
     *
     * 官方题解
     *
     * 写题解
     * 1024
     * 小红书特训计划
     * 掌握小红书面试高频考点
     * 执行用时分布
     * 1
     * ms
     * 击败
     * 100.00%
     * 复杂度分析
     * 消耗内存分布
     * 41.43
     * MB
     * 击败
     * 84.43%
     */
    public String multiply(String num1, String num2) {

        int[] temp = new int[num1.length() + num2.length() - 1];
        for (int i = 0; i < num1.length(); i++) {
            int a = num1.charAt(num1.length() - 1 - i) - '0';
            for (int j = 0; j < num2.length(); j++) {
                int b = num2.charAt(num2.length() - 1 - j) - '0';
                temp[i + j] += a * b;
            }
        }
        int carry = 0;
        for (int i = 0; i < temp.length; i++) {
            int cur = temp[i] + carry;
            temp[i] = cur % 10;
            carry = cur / 10;
        }
        // 取出先导0
        int i = temp.length - 1;
        while (i >= 0 && temp[i] == 0) {
            i--;
        }
        StringBuilder builder = new StringBuilder();
        while (i >= 0) {
            builder.append(temp[i]);
            i--;
        }
        return builder.length() > 0 ? builder.toString() : "0";
    }
}
