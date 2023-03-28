package com.qkx.example.lc;

//You have planned some train traveling one year in advance. The days of the
//year in which you will travel are given as an integer array days. Each day is an
//integer from 1 to 365.
//
// Train tickets are sold in three different ways:
//
//
// a 1-day pass is sold for costs[0] dollars,
// a 7-day pass is sold for costs[1] dollars, and
// a 30-day pass is sold for costs[2] dollars.
//
//
// The passes allow that many days of consecutive travel.
//
//
// For example, if we get a 7-day pass on day 2, then we can travel for 7 days:
//2, 3, 4, 5, 6, 7, and 8.
//
//
// Return the minimum number of dollars you need to travel every day in the
//given list of days.
//
//
// Example 1:
//
//
//Input: days = [1,4,6,7,8,20], costs = [2,7,15]
//Output: 11
//Explanation: For example, here is one way to buy passes that lets you travel
//your travel plan:
//On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
//On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4,
//..., 9.
//On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
//In total, you spent $11 and covered all the days of your travel.
//
//
// Example 2:
//
//
//Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
//Output: 17
//Explanation: For example, here is one way to buy passes that lets you travel
//your travel plan:
//On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2,
// ..., 30.
//On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
//In total, you spent $17 and covered all the days of your travel.
//
//
//
// Constraints:
//
//
// 1 <= days.length <= 365
// 1 <= days[i] <= 365
// days is in strictly increasing order.
// costs.length == 3
// 1 <= costs[i] <= 1000
//
//
// Related Topics Array Dynamic Programming 👍 6424 👎 112


import java.lang.reflect.Array;
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class No983 {

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了77.14% 的Java用户
     * 	内存消耗:40.5 MB,击败了58.39% 的Java用户
     * 	O(k*n*max(pass))
     */
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] passes = {1, 7, 30};
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = n - 1; i >= 0; i--) {
            for (int k = 0; k < 3; k++) {
                int cur = days[i], next = cur + passes[k] - 1;
                int t = i;
                while (t < n && days[t] <= next) {
                    t++;
                }
                int cost = t < n ? costs[k] + dp[t] : costs[k];
                dp[i] = Math.min(dp[i], cost);
            }
        }
        return dp[0];
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了42.53% 的Java用户
     * 	内存消耗:41 MB,击败了36.66% 的Java用户
     * 	O(k*n*log(n))
     */
    public int mincostTickets2(int[] days, int[] costs) {
        int n = days.length;
        int[] passes = {1, 7, 30};
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = n - 1; i >= 0; i--) {
            for (int k = 0; k < 3; k++) {
                int cur = days[i], next = cur + passes[k] - 1;
                int l = i, r = n - 1;
                while (l <= r) {
                    int mid = l + ((r - l) >> 1);
                    if (days[mid] <= next) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                int cost = l < n ? costs[k] + dp[l] : costs[k];
                dp[i] = Math.min(dp[i], cost);
            }
        }
        return dp[0];
    }


    private int query(int target, int[] days) {
        int l = 0, r = days.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (days[mid] == target) {
                return mid;
            }
        }
        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
