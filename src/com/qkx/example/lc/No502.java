package com.qkx.example.lc;

//Suppose LeetCode will start its IPO soon. In order to sell a good price of
//its shares to Venture Capital, LeetCode would like to work on some projects to
//increase its capital before the IPO. Since it has limited resources, it can only
//finish at most k distinct projects before the IPO. Help LeetCode design the best
//way to maximize its total capital after finishing at most k distinct projects.
//
// You are given n projects where the iᵗʰ project has a pure profit profits[i]
//and a minimum capital of capital[i] is needed to start it.
//
// Initially, you have w capital. When you finish a project, you will obtain
//its pure profit and the profit will be added to your total capital.
//
// Pick a list of at most k distinct projects from given projects to maximize
//your final capital, and return the final maximized capital.
//
// The answer is guaranteed to fit in a 32-bit signed integer.
//
//
// Example 1:
//
//
//Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
//Output: 4
//Explanation: Since your initial capital is 0, you can only start the project
//indexed 0.
//After finishing it you will obtain profit 1 and your capital becomes 1.
//With capital 1, you can either start the project indexed 1 or the project
//indexed 2.
//Since you can choose at most 2 projects, you need to finish the project
//indexed 2 to get the maximum capital.
//Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
//
//
// Example 2:
//
//
//Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
//Output: 6
//
//
//
// Constraints:
//
//
// 1 <= k <= 10⁵
// 0 <= w <= 10⁹
// n == profits.length
// n == capital.length
// 1 <= n <= 10⁵
// 0 <= profits[i] <= 10⁴
// 0 <= capital[i] <= 10⁹
//
//
// Related Topics Array Greedy Sorting Heap (Priority Queue) 👍 2542 👎 163


import java.sql.Array;
import java.util.Arrays;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class No502 {
    /**
     * 解答成功:
     * 	执行耗时:100 ms,击败了93.31% 的Java用户
     * 	内存消耗:62.5 MB,击败了83.95% 的Java用户
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        Profit[] arr = new Profit[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Profit(profits[i], capital[i]);
        }
        Arrays.sort(arr, (a, b) -> a.capital - b.capital);
        PriorityQueue<Profit> queue = new PriorityQueue<>(n, (a, b) -> b.profit - a.profit);
        int p = 0;
        for (int i = 0; i < k; i++) {
            while (p < n && arr[p].capital <= w) {
                queue.add(arr[p]);
                p++;
            }
            if (queue.isEmpty()) {
                return w;
            }
            w += queue.poll().profit;
        }
        return w;
    }

    class Profit {
        int profit;
        int capital;

        public Profit(int profit, int capital) {
            this.profit = profit;
            this.capital = capital;
        }
    }

    public static void main(String[] args) {
        int[] p = {1, 2, 3};
        int[] cp = {0, 1, 1};
        System.out.println(new No502().findMaximizedCapital(2, 0, p, cp));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

