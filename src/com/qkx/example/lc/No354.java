package com.qkx.example.lc;

//You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi]
//represents the width and the height of an envelope.
//
// One envelope can fit into another if and only if both the width and height
//of one envelope are greater than the other envelope's width and height.
//
// Return the maximum number of envelopes you can Russian doll (i.e., put one
//inside the other).
//
// Note: You cannot rotate an envelope.
//
//
// Example 1:
//
//
//Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
//Output: 3
//Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3]
//=> [5,4] => [6,7]).
//
//
// Example 2:
//
//
//Input: envelopes = [[1,1],[1,1],[1,1]]
//Output: 1
//
//
//
// Constraints:
//
//
// 1 <= envelopes.length <= 10⁵
// envelopes[i].length == 2
// 1 <= wi, hi <= 10⁵
//
//
// Related Topics Array Binary Search Dynamic Programming Sorting 👍 4822 👎 118
//


import com.qkx.example.utils.ArrayUtil;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class No354 {

    public static void main(String[] args) {
        int[][] e = new int[][]{{30,50},{12,2},{3,4},{12,15}};
        System.out.println(new No354().maxEnvelopes(e));
    }

    /**
     * 解答成功:
     * 	执行耗时:85 ms,击败了52.29% 的Java用户
     * 	内存消耗:81.7 MB,击败了55.68% 的Java用户
     */
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return -a[1] + b[1];
            }
        });
        int n = envelopes.length;
        int[] d = new int[n + 1];
        d[1] = envelopes[0][1];
        int len = 1;
        for (int i = 1; i < n; i++) {
            int l = 0, r = len;
            // 在 d 数组中找到最后一个比 y 小的位置
            while (l < r) {
                int mid = r - ((r - l) >> 1);
                if (d[mid] < envelopes[i][1]) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            d[l + 1] = envelopes[i][1];
            len = l == len ? len + 1 : len;
        }
        return len;
    }

    /**
     * Time Limit Exceeded
     */
    public int maxEnvelopes2(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });
        int n = envelopes.length;
        int[] dp = new int[n];
        int ans = 1;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
