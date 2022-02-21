package com.qkx.example.lc.easy;

/**
 * Created by qkx on 16/9/20.
 */
public class No121 {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }

        int res = 0;
        int min = prices[0];

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > min) {
                int profit = prices[i] - min;
                res = profit > res ? profit : res;
            } else {
                min = prices[i];
            }
        }

        return res;
    }
}
