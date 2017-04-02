package com.qkx.example.solutions.hard;

/**
 * Created by qkx on 16/9/20.
 */
public class No123 {
    public static int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }

        int buy;
        int sell;
        int profit;
        // preProfit
        int[] preProfit = new int[prices.length];
        preProfit[0] = 0;
        buy = prices[0];
        profit = 0;
        for (int i = 1; i <= prices.length - 1; i++) {
            if (prices[i] > buy) {
                sell = prices[i];
                int curProfit = sell - buy;
                profit = curProfit > profit ? curProfit : profit;
            } else {
                buy = prices[i];
            }
            preProfit[i] = profit;
        }

        //postProfit
        int[] postProfit = new int[prices.length];
        postProfit[prices.length - 1] = 0;
        sell = prices[prices.length - 1];
        profit = 0;
        for (int i = prices.length - 2; i >= 0; i--) {
            if (prices[i] < sell) {
                buy = prices[i];
                int curProfit = sell - buy;
                profit = curProfit > profit ? curProfit : profit;
            } else {
                sell = prices[i];
            }
            postProfit[i] = profit;
        }

        // result
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            int curProfit = preProfit[i] + postProfit[i];
            res = curProfit > res ? curProfit : res;
        }

        return res;
    }
}
