package com.qkx.example.solutions.LeetCode.hard;

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

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author kaixin
 * @since 2021-09-08 17:00
 */
public class No239 {

    //Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
    //Output: [3,3,5,5,6,7]
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
//        int[] nums = {1, -1};
        int k = 3;
        int[] res = new No239().maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 维护一个双向队列
     * 每加入一个元素，这个元素左边比他小的元素都可以被忽略，队列尾部小的数据可以弹出
     * 每滑出一个元素，如果和队头相等，那么说明是此元素是最大元素，滑出队头；否则说明不是最大元素，不操作
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return null;
        }

        int[] result = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.getLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        result[0] = deque.getFirst();

        // 0 1 2 3 -> 窗口 [i, i + k - 1]
        // num[i - 1]元素出窗口
        // num[i + k]元素入窗口
        for (int i = 1; i <= result.length - 1; i++) {
            int out = nums[i - 1];
            int in = nums[i + k - 1];
            Integer max = deque.getFirst();
            if (max != null && out >= max) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && deque.getLast() < in) {
                deque.removeLast();
            }
            deque.addLast(in);
            result[i] = deque.getFirst();
        }
        return result;
    }
}
