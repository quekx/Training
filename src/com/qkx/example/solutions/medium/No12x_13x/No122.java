package com.qkx.example.solutions.medium.No12x_13x;

/**
 * Created by qkx on 16/9/20.
 */
public class No122 {
    public static int maxProfit(int[] prices) {
        int res = 0;

        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                res += prices[i + 1] - prices[i];
            }
        }

        return res;
    }
}
