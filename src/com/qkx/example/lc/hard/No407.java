package com.qkx.example.lc.hard;

import com.qkx.example.utils.ArrayUtil;

import java.util.PriorityQueue;

/**
 * @author kaixin
 * @since 2021-09-28 14:41
 */
//Given an m x n integer matrix heightMap representing the height of each unit
//cell in a 2D elevation map, return the volume of water it can trap after raining.
//
//
//
// Example 1:
//
//
//Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
//Output: 4
//Explanation: After the rain, water is trapped between the blocks.
//We have two small pounds 1 and 3 units trapped.
//The total volume of water trapped is 4.
//
//
// Example 2:
//
//
//Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3
//]]
//Output: 10
//
//
//
// Constraints:
//
//
// m == heightMap.length
// n == heightMap[i].length
// 1 <= m, n <= 200
// 0 <= heightMap[i][j] <= 2 * 10⁴
//
// Related Topics Array Breadth-First Search Heap (Priority Queue) Matrix 👍 220
//4 👎 47


//leetcode submit region begin(Prohibit modification and deletion)
public class No407 {

    // 解答失败:
    //			测试用例:[[9,9,9,9,9,9,8,9,9,9,9],[9,0,0,0,0,0,1,0,0,0,9],[9,0,0,0,0,0,0,0,0,0,9],[9,0,0,0,0,0,0,0,0,0,9],[9,9,9,9,9,9,9,9,9,9,9]]
    //			测试结果:230
    //			期望结果:215

    // 解答失败:
    //			测试用例:[[19383,10886,12777,16915,17793,18335,15386,10492,16649,11421],[12362,27,8690,59,7763,3926,540,3426,9172,5736],[15211,5368,2567,6429,5782,1530,2862,5123,4067,3135],[13929,9802,4022,3058,3069,8167,1393,8456,5011,8042],[16229,7373,4421,4919,3784,8537,5198,4324,8315,4370],[16413,3526,6091,8980,9956,1873,6862,9170,6996,7281],[12305,925,7084,6327,336,6505,846,1729,1313,5857],[16124,3895,9582,545,8814,3367,5434,364,4043,3750],[11087,6808,7276,7178,5788,3584,5403,2651,2754,2399],[19932,5060,9676,3368,7739,12,6226,8586,8094,7539]]
    //			测试结果:83952
    //			期望结果:79058
    // 解答失败:
    //			测试用例:[[14,20,11,19,19,16],[11,10,7,4,9,6],[17,2,2,6,10,9],[15,9,2,1,4,1],[15,5,5,5,8,7],[14,2,8,6,10,7]]
    //			测试结果:17
    //			期望结果:11
    public static void main(String[] args) {
//        int[][] heightMap = {{19383, 10886, 12777, 16915, 17793, 18335, 15386, 10492, 16649, 11421}, {12362, 27, 8690, 59, 7763, 3926, 540, 3426, 9172, 5736}, {15211, 5368, 2567, 6429, 5782, 1530, 2862, 5123, 4067, 3135}, {13929, 9802, 4022, 3058, 3069, 8167, 1393, 8456, 5011, 8042}, {16229, 7373, 4421, 4919, 3784, 8537, 5198, 4324, 8315, 4370}, {16413, 3526, 6091, 8980, 9956, 1873, 6862, 9170, 6996, 7281}, {12305, 925, 7084, 6327, 336, 6505, 846, 1729, 1313, 5857}, {16124, 3895, 9582, 545, 8814, 3367, 5434, 364, 4043, 3750}, {11087, 6808, 7276, 7178, 5788, 3584, 5403, 2651, 2754, 2399}, {19932, 5060, 9676, 3368, 7739, 12, 6226, 8586, 8094, 7539}};
        int[][] heightMap = {{14, 20, 11, 19, 19, 16}, {11, 10, 7, 4, 9, 6}, {17, 2, 2, 6, 10, 9}, {15, 9, 2, 1, 4, 1}, {15, 5, 5, 5, 8, 7}, {14, 2, 8, 6, 10, 7}};
        ArrayUtil.print(heightMap);
        System.out.println("===========");
        System.out.println(new No407().trapRainWater(heightMap));
    }

    /**
     * 优先级队列 + bfs
     * 解答成功: 执行耗时:33 ms,击败了75.99% 的Java用户 内存消耗:45.8 MB,击败了85.49% 的Java用户
     */
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0) {
            return 0;
        }
        int m = heightMap.length, n = heightMap[0].length;
        if (m <= 2 || n <= 2) {
            return 0;
        }

        boolean[][] mark = new boolean[m][n];
        mark[0][0] = true;
        mark[0][n - 1] = true;
        mark[m - 1][0] = true;
        mark[m - 1][n - 1] = true;
        PriorityQueue<int[]> q = new PriorityQueue<>(2 * m + 2 * n, (a, b) -> a[0] - b[0]);
        for (int i = 1; i <= m - 2; i++) {
            q.add(new int[]{heightMap[i][0], i, 0});
            mark[i][0] = true;
            q.add(new int[]{heightMap[i][n - 1], i, n - 1});
            mark[i][n - 1] = true;
        }
        for (int i = 1; i <= n - 2; i++) {
            q.add(new int[]{heightMap[0][i], 0, i});
            mark[0][i] = true;
            q.add(new int[]{heightMap[m - 1][i], m - 1, i});
            mark[m - 1][i] = true;
        }

        int[][] ds = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int ans = 0;
        int min = 0;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int h = p[0], x = p[1], y = p[2];
            if (min > h) {
                ans += min - h;
            } else {
                min = h;
            }

            for (int[] d : ds) {
                int a = x + d[0], b = y + d[1];
                if (a >= 0 && a < m && b >= 0 && b < n && !mark[a][b]) {
                    q.add(new int[]{heightMap[a][b], a ,b});
                    mark[a][b] = true;
                }
            }
        }

        return ans;
    }

    /**
     * 以下逻辑有问题！！！！！！！
     * <p>
     * 类似一维数组盛水题
     * 一维转二维
     * 一维数组num[i]的做法是：
     * 对于每个num[i]，找到
     * 1. i 左侧最大值高度 num[k]
     * 2. i 右侧最大值高度 num[m]
     * 对于 i 位置的盛水量就是 min(num[k], num[m]) - num[i]
     * <p>
     * 扩展到二维
     * 对于每个num[i][j]
     * 左上向右下遍历：
     * num[i][j] 左侧最大高度 num[i][j-1]
     * num[i][j] 上侧最大高度 num[i-1][m]
     * 左上最大盛水量 l = min(num[i][j-1], num[i-1][m]))
     * <p>
     * 右下向左上遍历：
     * num[i][j] 右侧最大高度 num[i][j+1]
     * num[i][j] 上侧最大高度 num[i+1][m]
     * 左上最大盛水量 r = min(num[i][j+1], num[i+1][m]))
     * <p>
     * 最大成水量 = min(l, r) - num[i][m]
     *
     * @param heightMap
     * @return
     */
    public int trapRainWater2(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0) {
            return 0;
        }
        int maxX = heightMap.length;
        int maxY = heightMap[0].length;
        int[][] leftTop = new int[maxX][maxY];
        int[][] rightBottom = new int[maxX][maxY];
        int[][] leftBottom = new int[maxX][maxY];
        int[][] rightTop = new int[maxX][maxY];
        for (int x = 0; x <= maxX - 1; x++) {
            leftTop[x][0] = heightMap[x][0];
            rightBottom[x][maxY - 1] = heightMap[x][maxY - 1];

            leftBottom[x][0] = heightMap[x][0];
            rightTop[x][maxY - 1] = heightMap[x][maxY - 1];
        }
        for (int y = 0; y <= maxY - 1; y++) {
            leftTop[0][y] = heightMap[0][y];
            rightBottom[maxX - 1][y] = heightMap[maxX - 1][y];

            leftBottom[maxX - 1][y] = heightMap[maxX - 1][y];
            rightTop[0][y] = heightMap[0][y];
        }
        // left top
        for (int x = 1; x <= maxX - 1; x++) {
            for (int y = 1; y <= maxY - 1; y++) {
                int leftTopHeight = Math.min(leftTop[x - 1][y], leftTop[x][y - 1]);
                leftTop[x][y] = Math.max(leftTopHeight, heightMap[x][y]);
            }
        }
        // right bottom
        for (int x = maxX - 2; x >= 0; x--) {
            for (int y = maxY - 2; y >= 0; y--) {
                int rightBottomHeight = Math.min(rightBottom[x + 1][y], rightBottom[x][y + 1]);
                rightBottom[x][y] = Math.max(rightBottomHeight, heightMap[x][y]);
            }
        }
        // left bottom
        for (int x = maxX - 2; x >= 0; x--) {
            for (int y = 1; y <= maxY - 1; y++) {
                int leftBottomHeight = Math.min(leftBottom[x + 1][y], leftBottom[x][y - 1]);
                leftBottom[x][y] = Math.max(leftBottomHeight, heightMap[x][y]);
            }
        }
        // right top
        for (int x = 1; x <= maxX - 1; x++) {
            for (int y = maxY - 2; y >= 0; y--) {
                int rightTopHeight = Math.min(rightTop[x - 1][y], rightTop[x][y + 1]);
                rightTop[x][y] = Math.max(rightTopHeight, heightMap[x][y]);
            }
        }

        int[][] tmp = new int[maxX][maxY];
        int result = 0;
        for (int x = 0; x <= maxX - 1; x++) {
            for (int y = 0; y <= maxY - 1; y++) {
                int height = Math.min(leftTop[x][y], rightBottom[x][y]);
                height = Math.min(height, leftBottom[x][y]);
                height = Math.min(height, rightTop[x][y]);
                result += height - heightMap[x][y];

                tmp[x][y] = height;
            }
        }
        ArrayUtil.print(tmp);
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

