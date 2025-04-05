package com.qkx.example.lc.achive;

//You are given two integer arrays nums1 and nums2 sorted in ascending order
//and an integer k.
//
// Define a pair (u, v) which consists of one element from the first array and
//one element from the second array.
//
// Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
//
//
//
// Example 1:
//
//
//Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
//Output: [[1,2],[1,4],[1,6]]
//Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,
//6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
//
//
// Example 2:
//
//
//Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
//Output: [[1,1],[1,1]]
//Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,
//2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
//
//
// Example 3:
//
//
//Input: nums1 = [1,2], nums2 = [3], k = 3
//Output: [[1,3],[2,3]]
//Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
//
//
//
// Constraints:
//
//
// 1 <= nums1.length, nums2.length <= 10⁵
// -10⁹ <= nums1[i], nums2[i] <= 10⁹
// nums1 and nums2 both are sorted in ascending order.
// 1 <= k <= 10⁴
//
//
// Related Topics Array Heap (Priority Queue) 👍 3917 👎 229


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class No373 {
    /**
     * 解答成功:
     * 	执行耗时:84 ms,击败了13.21% 的Java用户
     * 	内存消耗:56.8 MB,击败了74.36% 的Java用户
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(k, (a, b) -> b[2] - a[2]);
        for (int a : nums1) {
            for (int b : nums2) {
                int sum = a + b;
                if (queue.size() < k) {
                    queue.add(new int[]{a, b, sum});
                } else if (sum < queue.peek()[2]) {
                    queue.add(new int[]{a, b, sum});
                    queue.poll();
                } else {
                    break;
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>(queue.size());
        for (int[] e : queue) {
            ans.add(Arrays.asList(e[0], e[1]));
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

