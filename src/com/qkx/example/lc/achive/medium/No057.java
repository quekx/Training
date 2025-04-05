package com.qkx.example.lc.achive.medium;

import com.qkx.example.utils.ArrayUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author kaixin
 * @since 2020-12-15 14:27
 */
//Given a set of non-overlapping intervals, insert a new interval into the inter
//vals (merge if necessary).
//
// You may assume that the intervals were initially sorted according to their st
//art times.
//
//
// Example 1:
//
//
//Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
//Output: [[1,5],[6,9]]
//
//
// Example 2:
//
//
//Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//Output: [[1,2],[3,10],[12,16]]
//Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
//
//
// Example 3:
//
//
//Input: intervals = [], newInterval = [5,7]
//Output: [[5,7]]
//
//
// Example 4:
//
//
//Input: intervals = [[1,5]], newInterval = [2,3]
//Output: [[1,5]]
//
//
// Example 5:
//
//
//Input: intervals = [[1,5]], newInterval = [2,7]
//Output: [[1,7]]
//
//
//
// Constraints:
//
//
// 0 <= intervals.length <= 104
// intervals[i].length == 2
// 0 <= intervals[i][0] <= intervals[i][1] <= 105
// intervals is sorted by intervals[i][0] in ascending order.
// newInterval.length == 2
// 0 <= newInterval[0] <= newInterval[1] <= 105
//
// Related Topics Array Sort
// 👍 2380 👎 227


//leetcode submit region begin(Prohibit modification and deletion)
public class No057 {

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        Object[] x = a.toArray();
//        Arrays.toString()
        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = {4,8};
        int[][] res = new No057().insert(intervals, newInterval);
        ArrayUtil.print(res);
    }

    /**
     * intervals[i]
     * 1. intervals[i][1] < newInterval[0] || intervals[i][0] > newInterval[1]
     * intervals[i] 和 newInterval 没有重叠，略过
     * 2. 其他情况存在重叠
     * new[0] = min(intervals[i][0], newInterval[0])
     * new[1] = max(intervals[i][1], newInterval[1])
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (newInterval == null || newInterval.length == 0) {
            return intervals;
        }
        if (intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }
        List<int[]> list = new LinkedList<>();
        boolean isFinish = false;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][1] < newInterval[0]) {
                list.add(intervals[i]);
            } else if (intervals[i][0] > newInterval[1]) {
                if (!isFinish) {
                    list.add(newInterval);
                    isFinish = true;
                }
                list.add(intervals[i]);
            } else {
                newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
                newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            }
        }

        if (!isFinish) {
            list.add(newInterval);
        }

        int[][] result = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

