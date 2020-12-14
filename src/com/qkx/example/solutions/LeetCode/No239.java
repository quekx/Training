package com.qkx.example.solutions.LeetCode;

/**
 * @author kaixin
 * @since 2020-12-14 16:09
 */
//You are given an array of integers nums, there is a sliding window of size k w
//hich is moving from the very left of the array to the very right. You can only s
//ee the k numbers in the window. Each time the sliding window moves right by one
//position.
//
// Return the max sliding window.
//
//
// Example 1:
//
//
//Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
//Output: [3,3,5,5,6,7]
//Explanation:
//Window position                Max
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
//
//
// Example 2:
//
//
//Input: nums = [1], k = 1
//Output: [1]
//
//
// Example 3:
//
//
//Input: nums = [1,-1], k = 1
//Output: [1,-1]
//
//
// Example 4:
//
//
//Input: nums = [9,11], k = 2
//Output: [11]
//
//
// Example 5:
//
//
//Input: nums = [4,-2], k = 2
//Output: [4]
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 105
// -104 <= nums[i] <= 104
// 1 <= k <= nums.length
//
// Related Topics Heap Sliding Window Dequeue
// 👍 4746 👎 203


//leetcode submit region begin(Prohibit modification and deletion)
public class No239 {
    /**
     * //[1  3  -1] -3  5  3  6  7       3
     * // 1 [3  -1  -3] 5  3  6  7       3
     * // 1  3 [-1  -3  5] 3  6  7       5
     * // 1  3  -1 [-3  5  3] 6  7       5
     * // 1  3  -1  -3 [5  3  6] 7       6
     * // 1  3  -1  -3  5 [3  6  7]      7
     * 大根堆只能保持最大值
     * 遍历过程中新元素添加和旧元素删除
     * 需要知道新元素和旧元素在堆数组中的位置
     * 然后进行调整
     *
     * 维护一个容量为k的大根堆 heap[k]
     * 堆内存的数据为数据数组个元素的下标偏移量
     * 数据范围为a[i] ~ a[i + k - 1]; i 从 0 开始遍历
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

