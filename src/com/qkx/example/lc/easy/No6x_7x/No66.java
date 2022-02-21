package com.qkx.example.lc.easy.No6x_7x;

/**
 * Created by qkx on 16/5/26.
 */
public class No66 {
    public int[] plusOne(int[] digits) {
        int len = digits.length;

        for (int i = len - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + 1;
                break;
            }
        }

        if (digits[0] == 0) {
            int[] res = new int[len + 1];
            res[0] = 1;
            System.arraycopy(digits, 0, res, 1, len);
            return res;
        } else {
            return digits;
        }
    }
}
