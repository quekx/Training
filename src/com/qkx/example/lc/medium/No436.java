package com.qkx.example.lc.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author kaixin
 * @since 2021-11-17 19:42
 */
//You are given an array of intervals, where intervals[i] = [starti, endi] and
//each starti is unique.
//
// The right interval for an interval i is an interval j such that startj >=
//endi and startj is minimized.
//
// Return an array of right interval indices for each interval i. If no right
//interval exists for interval i, then put -1 at index i.
//
//
// Example 1:
//
//
//Input: intervals = [[1,2]]
//Output: [-1]
//Explanation: There is only one interval in the collection, so it outputs -1.
//
//
// Example 2:
//
//
//Input: intervals = [[3,4],[2,3],[1,2]]
//Output: [-1,0,1]
//Explanation: There is no right interval for [3,4].
//The right interval for [2,3] is [3,4] since start0 = 3 is the smallest start
//that is >= end1 = 3.
//The right interval for [1,2] is [2,3] since start1 = 2 is the smallest start
//that is >= end2 = 2.
//
//
// Example 3:
//
//
//Input: intervals = [[1,4],[2,3],[3,4]]
//Output: [-1,2,-1]
//Explanation: There is no right interval for [1,4] and [3,4].
//The right interval for [2,3] is [3,4] since start2 = 3 is the smallest start
//that is >= end1 = 3.
//
//
//
// Constraints:
//
//
// 1 <= intervals.length <= 2 * 10⁴
// intervals[i].length == 2
// -10⁶ <= starti <= endi <= 10⁶
// The start point of each interval is unique.
//
// Related Topics Array Binary Search Sorting 👍 980 👎 227


//leetcode submit region begin(Prohibit modification and deletion)
public class No436 {

    /**
     * 解答成功:
     * 执行耗时:8 ms,击败了99.10% 的Java用户
     * 内存消耗:43.1 MB,击败了86.08% 的Java用户
     */
    public static void main(String[] args) {
        int[][] intervals = {{1,4}, {2,3}, {3,4}};
        System.out.println(Arrays.toString(new No436().findRightInterval(intervals)));
    }

    public int[] findRightInterval(int[][] intervals) {
        int len = intervals.length;
        Node[] nodes = new Node[len];
        for (int i = 0; i < len; i++) {
            nodes[i] = new Node(intervals[i], i);
        }
        Arrays.sort(nodes, new MyComparator());

        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = search(nodes, intervals[i][1]);
        }
        return result;
    }

    private int search(Node[] nodes, int intervalEnd) {
        int start = 0;
        int end = nodes.length - 1;
        while (start <= end) {
            int middle = start + ((end - start) >> 1);
            Node cur = nodes[middle];
            if (intervalEnd == cur.interval[0]) {
                return cur.index;
            } else if (intervalEnd < cur.interval[0]) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return start <= nodes.length - 1 ? nodes[start].index : -1;
    }

    private static class Node {
        private int[] interval;
        private int index;

        public Node(int[] interval, int index) {
            this.interval = interval;
            this.index = index;
        }
    }

    private static class MyComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            if (o1.interval[0] != o2.interval[0]) {
                return o1.interval[0] - o2.interval[0];
            }
            return o1.interval[1] - o2.interval[1];
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

