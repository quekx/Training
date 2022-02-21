package com.qkx.example.lc;

import java.util.*;

/**
 * @author kaixin
 * @since 2020-12-10 20:55
 */
//A city's skyline is the outer contour of the silhouette formed by all the buil
//dings in that city when viewed from a distance. Given the locations and heights
//of all the buildings, return the skyline formed by these buildings collectively.
//
//
// The geometric information of each building is given in the array buildings wh
//ere buildings[i] = [lefti, righti, heighti]:
//
//
// lefti is the x coordinate of the left edge of the ith building.
// righti is the x coordinate of the right edge of the ith building.
// heighti is the height of the ith building.
//
//
// You may assume all buildings are perfect rectangles grounded on an absolutely
// flat surface at height 0.
//
// The skyline should be represented as a list of "key points" sorted by their x
//-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoi
//nt of some horizontal segment in the skyline except the last point in the list,
//which always has a y-coordinate 0 and is used to mark the skyline's termination
//where the rightmost building ends. Any ground between the leftmost and rightmost
// buildings should be part of the skyline's contour.
//
// Note: There must be no consecutive horizontal lines of equal height in the ou
//tput skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acc
//eptable; the three lines of height 5 should be merged into one in the final outp
//ut as such: [...,[2 3],[4 5],[12 7],...]
//
//
// Example 1:
//
//
//Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
//Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
//Explanation:
//Figure A shows the buildings of the input.
//Figure B shows the skyline formed by those buildings. The red points in figure
// B represent the key points in the output list.
//
//
// Example 2:
//
//
//Input: buildings = [[0,2,3],[2,5,3]]
//Output: [[0,3],[5,0]]
//
//
//
// Constraints:
//
//
// 1 <= buildings.length <= 104
// 0 <= lefti < righti <= 231 - 1
// 1 <= heighti <= 231 - 1
// buildings is sorted by lefti in non-decreasing order.
//
// Related Topics Divide and Conquer Heap Binary Indexed Tree Segment Tree Line
//Sweep
// 👍 2556 👎 144


//leetcode submit region begin(Prohibit modification and deletion)
public class No218 {
    public static void main(String[] args) {
        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
//        int[][] buildings = {{0, 2, 3}, {2, 5, 3}};
        List<List<Integer>> res = new No218().getSkyline(buildings);
        System.out.println(res);
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        Arrays.sort(buildings, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int len = buildings.length;
        int[] pos = new int[len * 2];
        for (int i = 0; i < len; i++) {
            pos[i * 2] = buildings[i][0];
            pos[i * 2 + 1] = buildings[i][1];
        }
        Arrays.sort(pos);

        List<List<Integer>> res = new LinkedList<>();
        Set<int[]> set = new HashSet<>();
        int preHeight = 0;
        int i = 0;
        int positionI = 0;
        while ((i < len || !set.isEmpty()) && positionI < pos.length) {
            int k;
            for (k = i; k < len; k++) {
                // 如果x > pos，加入当前集合
                if (buildings[k][0] <= pos[positionI]) {
                    set.add(buildings[k]);
                } else {
                    break;
                }
            }

            i = k;
            int maxHeight = 0;
            Iterator<int[]> itor = set.iterator();
            while (itor.hasNext()) {
                int[] building = itor.next();
                if (building[1] <= pos[positionI]) {
                    itor.remove();
                } else {
                    maxHeight = Math.max(maxHeight, building[2]);
                }
            }

            if (maxHeight != preHeight) {
                res.add(Arrays.asList(pos[positionI], maxHeight));
                preHeight = maxHeight;
            }
            positionI++;
        }
        return res;
    }

    /**
     * 运行失败:
     * Time Limit Exceeded
     * 测试用例:[[0,2147483647,2147483647]]
     *
     * @param buildings
     * @return
     */
    public List<List<Integer>> getSkyline2(int[][] buildings) {

        Arrays.sort(buildings, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<List<Integer>> res = new LinkedList<>();
        Set<int[]> set = new HashSet<>();
        int preHeight = 0;
        int i = 0;
        int positionX = 0;
        int len = buildings.length;
        while (i < len || !set.isEmpty()) {
            int k;
            for (k = i; k < len; k++) {
                // 如果x > pos，加入当前集合
                if (buildings[k][0] <= positionX) {
                    set.add(buildings[k]);
                } else {
                    break;
                }
            }
            i = k;
            int maxHeight = 0;
            Iterator<int[]> itor = set.iterator();
            while (itor.hasNext()) {
                int[] building = itor.next();
                if (building[1] <= positionX) {
                    itor.remove();
                } else {
                    maxHeight = Math.max(maxHeight, building[2]);
                }
            }
            if (maxHeight != preHeight) {
                res.add(Arrays.asList(positionX, maxHeight));
                preHeight = maxHeight;
            }
            positionX++;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
