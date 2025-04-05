package com.qkx.example.lc.achive.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author kaixin
 * @since 2021-09-17 15:01
 */
//Given an array of intervals intervals where intervals[i] = [starti, endi],
//return the minimum number of intervals you need to remove to make the rest of the
//intervals non-overlapping.
//
//
// Example 1:
//
//
//Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
//Output: 1
//Explanation: [1,3] can be removed and the rest of the intervals are non-
//overlapping.
//
//
// Example 2:
//
//
//Input: intervals = [[1,2],[1,2],[1,2]]
//Output: 2
//Explanation: You need to remove two [1,2] to make the rest of the intervals
//non-overlapping.
//
//
// Example 3:
//
//
//Input: intervals = [[1,2],[2,3]]
//Output: 0
//Explanation: You don't need to remove any of the intervals since they're
//already non-overlapping.
//
//
//
// Constraints:
//
//
// 1 <= intervals.length <= 10⁵
// intervals[i].length == 2
// -5 * 10⁴ <= starti < endi <= 5 * 10⁴
//
// Related Topics Array Dynamic Programming Greedy Sorting 👍 2639 👎 75


//leetcode submit region begin(Prohibit modification and deletion)
public class No435 {

    public static void main(String[] args) {
        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
        int res = new No435().eraseOverlapIntervals(intervals);
        System.out.println(res);
        for (int[] row : intervals) {
            System.out.println(Arrays.toString(row));
        }
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                }
                return o2[0] - o1[0];
            }
        });

        int count = 1;
        int lastEnd = intervals[0][1];
        for (int i = 1; i <= intervals.length - 1; i++) {
            int[] cur = intervals[i];
            if (cur[0] >= lastEnd) {
                count++;
                lastEnd = cur[1];
            }
        }
        return intervals.length - count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

