package com.qkx.example.solutions.LeetCode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kaixin
 * @since 2021-09-22 17:05
 */
//You are given n points in the plane that are all distinct, where points[i] = [
//xi, yi]. A boomerang is a tuple of points (i, j, k) such that the distance
//between i and j equals the distance between i and k (the order of the tuple matters).
//
//
// Return the number of boomerangs.
//
//
// Example 1:
//
//
//Input: points = [[0,0],[1,0],[2,0]]
//Output: 2
//Explanation: The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]
//].
//
//
// Example 2:
//
//
//Input: points = [[1,1],[2,2],[3,3]]
//Output: 2
//
//
// Example 3:
//
//
//Input: points = [[1,1]]
//Output: 0
//
//
//
// Constraints:
//
//
// n == points.length
// 1 <= n <= 500
// points[i].length == 2
// -10⁴ <= xi, yi <= 10⁴
// All the points are unique.
//
// Related Topics Array Hash Table Math 👍 500 👎 793


//leetcode submit region begin(Prohibit modification and deletion)
public class No447 {

    public static void main(String[] args) {

    }

    /**
     * map:
     * {顶点坐标 -> {线段长度 -> 符合的点数}}
     * @param points
     * @return
     */
    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return 0;
        }

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            for (int k = i + 1; k < n; k++) {
                // [i, k]线段
                int dist = (points[i][0] - points[k][0]) * (points[i][0] - points[k][0])
                        + (points[i][1] - points[k][1]) * (points[i][1] - points[k][1]);
                Map<Integer, Integer> mapI = map.computeIfAbsent(i, t -> new HashMap<>());
                mapI.put(dist, mapI.getOrDefault(dist, 0) + 1);
                Map<Integer, Integer> mapK = map.computeIfAbsent(k, t -> new HashMap<>());
                mapK.put(dist, mapK.getOrDefault(dist, 0) + 1);
            }
        }
        int result = 0;
        for (Map<Integer, Integer> numMap : map.values()) {
            for (Integer num : numMap.values()) {
                if (num <= 1) {
                    continue;
                }
                result += num * (num - 1);
            }
        }

        return result;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

