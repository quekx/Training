package com.qkx.example.lc.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author kaixin
 * @since 2022-04-15 15:38
 */
//There is an m x n binary grid matrix with all the values set 0 initially.
//Design an algorithm to randomly pick an index (i, j) where matrix[i][j] == 0 and
//flips it to 1. All the indices (i, j) where matrix[i][j] == 0 should be equally
//likely to be returned.
//
// Optimize your algorithm to minimize the number of calls made to the built-in
//random function of your language and optimize the time and space complexity.
//
// Implement the Solution class:
//
//
// Solution(int m, int n) Initializes the object with the size of the binary
//matrix m and n.
// int[] flip() Returns a random index [i, j] of the matrix where matrix[i][j] =
//= 0 and flips it to 1.
// void reset() Resets all the values of the matrix to be 0.
//
//
//
// Example 1:
//
//
//Input
//["Solution", "flip", "flip", "flip", "reset", "flip"]
//[[3, 1], [], [], [], [], []]
//Output
//[null, [1, 0], [2, 0], [0, 0], null, [2, 0]]
//
//Explanation
//Solution solution = new Solution(3, 1);
//solution.flip();  // return [1, 0], [0,0], [1,0], and [2,0] should be equally
//likely to be returned.
//solution.flip();  // return [2, 0], Since [1,0] was returned, [2,0] and [0,0]
//solution.flip();  // return [0, 0], Based on the previously returned indices,
//only [0,0] can be returned.
//solution.reset(); // All the values are reset to 0 and can be returned.
//solution.flip();  // return [2, 0], [0,0], [1,0], and [2,0] should be equally
//likely to be returned.
//
//
//
// Constraints:
//
//
// 1 <= m, n <= 10⁴
// There will be at least one free cell for each call to flip.
// At most 1000 calls will be made to flip and reset.
//
// Related Topics Hash Table Math Reservoir Sampling Randomized 👍 293 👎 89


//leetcode submit region begin(Prohibit modification and deletion)
public class No519 {

    /**
     * 用 map 作为映射
     * 将数组交换转化为映射关系变更，这样只需要维护数组大小，不需要维护整个数组
     * 在拿值的时候，注意处理映射关系
     *
     * 解答成功:
     * 	执行耗时:50 ms,击败了69.03% 的Java用户
     * 	内存消耗:50.9 MB,击败了36.28% 的Java用户
     */
    private static class Solution {

        private int m, n;
        private int size;

        private Random random;

        private Map<Integer, Integer> map;

        public Solution(int m, int n) {
            this.m = m;
            this.n = n;

            size = m * n;
            random = new Random();
            map = new HashMap<>();
        }

        public int[] flip() {
            int cur = random.nextInt(size);
            // 获取实际的编号
            int ac = map.getOrDefault(cur, cur);
            // 替换编号
            map.put(cur, map.getOrDefault(size - 1, size - 1));
            size--;
            int x = ac / n, y = ac - x * n;
            return new int[] {x, y};
        }

        public void reset() {
            size = m * n;
            map.clear();
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution(2, 2);
        System.out.println(Arrays.toString(s.flip()));
        System.out.println(Arrays.toString(s.flip()));
        System.out.println(Arrays.toString(s.flip()));
        System.out.println(Arrays.toString(s.flip()));
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(m, n);
 * int[] param_1 = obj.flip();
 * obj.reset();
 */
//leetcode submit region end(Prohibit modification and deletion)
}


