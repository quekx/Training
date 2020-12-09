package com.qkx.example.solutions.LeetCode.unfinish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kaixin
 * @since 2020-12-09 14:15
 */
//Given n points on a 2D plane, find the maximum number of points that lie on th
//e same straight line.
//
// Example 1:
//
//
//Input: [[1,1],[2,2],[3,3]]
//Output: 3
//Explanation:
//^
//|
//|        o
//|     o
//|  o  
//+------------->
//0  1  2  3  4
//
//
// Example 2:
//
//
//Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
//Output: 4
//Explanation:
//^
//|
//|  o
//|     o        o
//|        o
//|  o        o
//+------------------->
//0  1  2  3  4  5  6
//
//
// NOTE: input types have been changed on April 15, 2019. Please reset to defaul
//t code definition to get new method signature.
// Related Topics Hash Table Math
// 👍 1024 👎 2206


//leetcode submit region begin(Prohibit modification and deletion)
public class No149 {
    public static void main(String[] args) {
        int[][] points = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        int[][] points1 = {{1, 1}, {1, 1}, {2, 2}, {2, 2}};
        System.out.println(new No149().maxPoints(points1));
    }

    public int maxPoints(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                String key = genKey(points[i], points[j]);
                Integer num = map.get(key);
                if (num == null) {
                    num = 2;
                } else {
                    num += 2;
                }
                map.put(key, num);
            }
        }
        int max = 0;
        for (Integer num : map.values()) {
            max = Math.max(max, num);
        }
        max = (int) Math.sqrt(max);
        return max + 1;
    }

    private String genKey(int[] m, int[] n) {
        if (m[0] == n[0]) {
            return "x=" + m[0];
        } else if (m[1] == n[1]) {
            return "y=" + m[1];
        } else {
            float a = ((float) (n[1] - m[1])) / (n[0] - m[0]);
            float b = ((float) (n[0] * m[1] - m[0] * n[1])) / (n[0] - m[0]);
            return a + "_" + b;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

