package com.qkx.example;

//You are given an array of integers nums, there is a sliding window of size k
//which is moving from the very left of the array to the very right. You can only
//see the k numbers in the window. Each time the sliding window moves right by one
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
// 1 <= nums.length <= 10⁵
// -10⁴ <= nums[i] <= 10⁴
// 1 <= k <= nums.length
//
// Related Topics Array Queue Sliding Window Heap (Priority Queue) Monotonic
//Queue 👍 7179 👎 262

/**
 * @author kaixin
 * @since 2021-09-08 17:00
 */
public class No239 {
    /**
     * 维护一个大根堆 heap
     * 滑动窗口在滑动时
     * 1. 如果划出的元素等于堆最大值，说明最大数被划出，重新调整堆
     * 如果划出的元素小于堆最大值，说明是无效数，忽略
     *
     * 2. 如果划入的元素大于堆最大值，说明前面的数都比这个数据小，可以丢弃，重新建堆
     * 如果划入的元素小于等于堆最大值，加入堆中
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        return null;
    }
}
