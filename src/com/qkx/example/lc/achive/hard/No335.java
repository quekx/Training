package com.qkx.example.lc.achive.hard;

//You are given an array of integers distance.
//
// You start at the point (0, 0) on an X-Y plane, and you move distance[0]
//meters to the north, then distance[1] meters to the west, distance[2] meters to the
//south, distance[3] meters to the east, and so on. In other words, after each
//move, your direction changes counter-clockwise.
//
// Return true if your path crosses itself or false if it does not.
//
//
// Example 1:
//
//
//Input: distance = [2,1,1,2]
//Output: true
//Explanation: The path crosses itself at the point (0, 1).
//
//
// Example 2:
//
//
//Input: distance = [1,2,3,4]
//Output: false
//Explanation: The path does not cross itself at any point.
//
//
// Example 3:
//
//
//Input: distance = [1,1,1,2,1]
//Output: true
//Explanation: The path crosses itself at the point (0, 0).
//
//
//
// Constraints:
//
//
// 1 <= distance.length <= 10⁵
// 1 <= distance[i] <= 10⁵
//
//
// Related Topics Array Math Geometry 👍 298 👎 477


//leetcode submit region begin(Prohibit modification and deletion)
class No335 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了61.64% 的Java用户
     * 	内存消耗:47.6 MB,击败了98.63% 的Java用户
     */
    /**
     * 四种相交的情形
     */
    public boolean isSelfCrossing(int[] distance) {
        for (int i = 3; i < distance.length; i++) {
            if (-distance[i - 2] + distance[i] >= 0
                    && distance[i - 3] - distance[i - 1] >= 0) {
                return true;
            }
            if (i >= 4
                    && -distance[i - 3] + distance[i - 1] <= 0
                    && -distance[i - 2] + distance[i] >= 0) {
                return true;
            }
            if (i >= 4
                    && -distance[i - 3] + distance[i - 1] == 0
                    && distance[i - 4] - distance[i - 2] + distance[i] >= 0) {
                return true;
            }
            if (i >= 5
                    && -distance[i - 4] + distance[i - 2] >= 0
                    && -distance[i - 4] + distance[i - 2] - distance[i] <= 0
                    && distance[i - 5] - distance[i - 3] + distance[i - 1] >= 0
                    && -distance[i - 3] + distance[i - 1] <= 0) {
                return true;
            }
        }
        return false;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
