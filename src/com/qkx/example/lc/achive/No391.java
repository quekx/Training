package com.qkx.example.lc.achive;

//Given an array rectangles where rectangles[i] = [xi, yi, ai, bi] represents
//an axis-aligned rectangle. The bottom-left point of the rectangle is (xi, yi) and
//the top-right point of it is (ai, bi).
//
// Return true if all the rectangles together form an exact cover of a
//rectangular region.
//
//
// Example 1:
//
//
//Input: rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
//Output: true
//Explanation: All 5 rectangles together form an exact cover of a rectangular
//region.
//
//
// Example 2:
//
//
//Input: rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
//Output: false
//Explanation: Because there is a gap between the two rectangular regions.
//
//
// Example 3:
//
//
//Input: rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
//Output: false
//Explanation: Because two of the rectangles overlap with each other.
//
//
//
// Constraints:
//
//
// 1 <= rectangles.length <= 2 * 10⁴
// rectangles[i].length == 4
// -10⁵ <= xi, yi, ai, bi <= 10⁵
//
//
// Related Topics Array Line Sweep 👍 729 👎 108


//leetcode submit region begin(Prohibit modification and deletion)
class No391 {

    public static void main(String[] args) {
        int[][] r = {{1,1,3,3},{3,1,4,2},{3,2,4,4},{1,3,2,4},{2,3,3,4}};
        System.out.println(new No391().isRectangleCover(r));
    }

    /**
     * Time Limit Exceeded
     */
    public boolean isRectangleCover(int[][] rectangles) {
        int sum = 0;
        int lbx = Integer.MAX_VALUE, lby = Integer.MAX_VALUE;
        int rtx = Integer.MIN_VALUE, rty = Integer.MIN_VALUE;
        for (int[] r : rectangles) {
            lbx = Math.min(lbx, r[0]);
            lby = Math.min(lby, r[1]);
            rtx = Math.max(rtx, r[2]);
            rty = Math.max(rty, r[3]);
            sum += (r[2] - r[0]) * (r[3] - r[1]);
        }
        if (sum != (rtx - lbx) * (rty - lby)) {
            return false;
        }
        for (int i = 0; i < rectangles.length; i++) {
            for (int k = i + 1; k < rectangles.length; k++) {
                if (isCross(rectangles[i], rectangles[k])) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isCross(int[] r1, int[] r2) {
        if (r1[0] == r2[0]) {
            if (r1[1] > r2[1]) {
                int[] t = r1;
                r1 = r2;
                r2 = t;
            }
            return r1[3] > r2[1];
        }

        if (r1[0] > r2[0]) {
            int[] t = r1;
            r1 = r2;
            r2 = t;
        }
        return r2[0] < r1[2] &&  r2[1] < r1[3] && r2[3] > r1[1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

