package com.qkx.example.lc;

//You have n super washing machines on a line. Initially, each washing machine
//has some dresses or is empty.
//
// For each move, you could choose any m (1 <= m <= n) washing machines, and
//pass one dress of each washing machine to one of its adjacent washing machines at
//the same time.
//
// Given an integer array machines representing the number of dresses in each
//washing machine from left to right on the line, return the minimum number of
//moves to make all the washing machines have the same number of dresses. If it is not
//possible to do it, return -1.
//
//
// Example 1:
//
//
//Input: machines = [1,0,5]
//Output: 3
//Explanation:
//1st move:    1     0 <-- 5    =>    1     1     4
//2nd move:    1 <-- 1 <-- 4    =>    2     1     3
//3rd move:    2     1 <-- 3    =>    2     2     2
//
//
// Example 2:
//
//
//Input: machines = [0,3,0]
//Output: 2
//Explanation:
//1st move:    0 <-- 3     0    =>    1     2     0
//2nd move:    1     2 --> 0    =>    1     1     1
//
//
// Example 3:
//
//
//Input: machines = [0,2,0]
//Output: -1
//Explanation:
//It's impossible to make all three washing machines have the same number of
//dresses.
//
//
//
// Constraints:
//
//
// n == machines.length
// 1 <= n <= 10⁴
// 0 <= machines[i] <= 10⁵
//
//
// Related Topics Array Greedy 👍 657 👎 202


/**
 * 解答成功:
 * 	执行耗时:1 ms,击败了70.48% 的Java用户
 * 	内存消耗:43.5 MB,击败了9.52% 的Java用户
 */
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMinMoves(int[] machines) {
        int n = machines.length, sum = 0;
        for (int m : machines) {
            sum += m;
        }
        if (sum % n != 0) {
            return -1;
        }
        int x = sum / n;
        int ans = 0;
        int delta = 0;
        for (int m : machines) {
            int cur = m + delta;
            int newDelta = cur - x;
            if (delta < 0 && newDelta > 0) {
                ans = Math.max(ans, Math.abs(delta) + Math.abs(newDelta));
            } else {
                ans = Math.max(ans, Math.abs(newDelta));
            }
            delta = newDelta;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

public class No517 {
    public static void main(String[] args) {
//        int[] m = {1,0,5};
//        int[] m = {4,0,0,4};
        int[] m = {9,1,8,8,9};
        System.out.println(new Solution().findMinMoves(m));
    }
}