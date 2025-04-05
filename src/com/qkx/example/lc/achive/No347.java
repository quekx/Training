package com.qkx.example.lc.achive;

//Given an integer array nums and an integer k, return the k most frequent
//elements. You may return the answer in any order.
//
//
// Example 1:
// Input: nums = [1,1,1,2,2,3], k = 2
//Output: [1,2]
//
// Example 2:
// Input: nums = [1], k = 1
//Output: [1]
//
//
// Constraints:
//
//
// 1 <= nums.length <= 10⁵
// -10⁴ <= nums[i] <= 10⁴
// k is in the range [1, the number of unique elements in the array].
// It is guaranteed that the answer is unique.
//
//
//
// Follow up: Your algorithm's time complexity must be better than O(n log n),
//where n is the array's size.
//
// Related Topics Array Hash Table Divide and Conquer Sorting Heap (Priority
//Queue) Bucket Sort Counting Quickselect 👍 12884 👎 475


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class No347 {
    /**
     * 解答成功:
     * 	执行耗时:14 ms,击败了42.52% 的Java用户
     * 	内存消耗:45.9 MB,击败了14.78% 的Java用户
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(k, Comparator.comparingInt(a -> a[1]));
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (queue.size() < k || queue.peek()[1] < entry.getValue()) {
                queue.add(new int[]{entry.getKey(), entry.getValue()});
            }
            if (queue.size() > k) {
                queue.poll();
            }
        }
        int[] ans = new int[queue.size()];
        int i = 0;
        while (!queue.isEmpty()) {
            ans[i++] = queue.poll()[0];
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

