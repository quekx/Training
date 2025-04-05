package com.qkx.example.lc.achive.medium;

import java.util.Arrays;

/**
 * @author kaixin
 * @since 2021-09-23 11:48
 */
//There are some spherical balloons taped onto a flat wall that represents the
//XY-plane. The balloons are represented as a 2D integer array points where points[
//i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches
//between xstart and xend. You do not know the exact y-coordinates of the balloons.
//
// Arrows can be shot up directly vertically (in the positive y-direction) from
//different points along the x-axis. A balloon with xstart and xend is burst by
//an arrow shot at x if xstart <= x <= xend. There is no limit to the number of
//arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any
//balloons in its path.
//
// Given the array points, return the minimum number of arrows that must be
//shot to burst all balloons.
//
//
// Example 1:
//
//
//Input: points = [[10,16],[2,8],[1,6],[7,12]]
//Output: 2
//Explanation: The balloons can be burst by 2 arrows:
//- Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
//- Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
//
//
// Example 2:
//
//
//Input: points = [[1,2],[3,4],[5,6],[7,8]]
//Output: 4
//Explanation: One arrow needs to be shot for each balloon for a total of 4
//arrows.
//
//
// Example 3:
//
//
//Input: points = [[1,2],[2,3],[3,4],[4,5]]
//Output: 2
//Explanation: The balloons can be burst by 2 arrows:
//- Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
//- Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
//
//
//
// Constraints:
//
//
// 1 <= points.length <= 10⁵
// points[i].length == 2
// -2³¹ <= xstart < xend <= 2³¹ - 1
//
// Related Topics Array Greedy Sorting 👍 2152 👎 74


//leetcode submit region begin(Prohibit modification and deletion)
public class No452 {

    public static void main(String[] args) {
        boolean x = -2147483645 < 2147483646;
        System.out.println(x);

        int[][] points = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        int[][] points2 = {{-2147483646,-2147483645}, {2147483646,2147483647}};
        int res = new No452().findMinArrowShots(points2);
        System.out.println(res);
    }

    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }

        Arrays.sort(points, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] < o2[0] ? -1 : 1;
            }
            return o1[1] < o2[1] ? -1 : 1;
        });

        int count = 1;
        int[] preArrow = points[0];
        for (int i = 1; i < points.length; i++) {
            int[] overlap = getOverlap(preArrow, points[i]);
            System.out.println(Arrays.toString(overlap));
            if (overlap == null) {
                count++;
                preArrow = points[i];
            } else {
                preArrow = overlap;
            }
        }
        return count;
    }

    private int[] getOverlap(int[] pre, int[] cur) {
        if (pre[1] < cur[0]) {
            return null;
        }
        return new int[]{cur[0], Math.min(pre[1], cur[1])};
    }
}
//leetcode submit region end(Prohibit modification and deletion)

