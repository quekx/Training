package com.qkx.example.lc.achive.hard;

//The median is the middle value in an ordered integer list. If the size of the
//list is even, there is no middle value. So the median is the mean of the two
//middle values.
//
//
// For examples, if arr = [2,3,4], the median is 3.
// For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
//
//
// You are given an integer array nums and an integer k. There is a sliding
//window of size k which is moving from the very left of the array to the very right.
//You can only see the k numbers in the window. Each time the sliding window
//moves right by one position.
//
// Return the median array for each window in the original array. Answers
//within 10⁻⁵ of the actual value will be accepted.
//
//
// Example 1:
//
//
//Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
//Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
//Explanation:
//Window position                Median
//---------------                -----
//[1  3  -1] -3  5  3  6  7        1
// 1 [3  -1  -3] 5  3  6  7       -1
// 1  3 [-1  -3  5] 3  6  7       -1
// 1  3  -1 [-3  5  3] 6  7        3
// 1  3  -1  -3 [5  3  6] 7        5
// 1  3  -1  -3  5 [3  6  7]       6
//
//
// Example 2:
//
//
//Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
//Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
//
//
//
// Constraints:
//
//
// 1 <= k <= nums.length <= 10⁵
// -2³¹ <= nums[i] <= 2³¹ - 1
//
//
// Related Topics Array Hash Table Sliding Window Heap (Priority Queue) 👍 2732
//👎 154


import com.qkx.example.utils.ArrayUtil;

import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class No480 {
    /**
     * 解答成功:
     * 	执行耗时:19 ms,击败了97.73% 的Java用户
     * 	内存消耗:44.7 MB,击败了30.61% 的Java用户
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int cap = (k + 1) >> 1;
        PriorityQueue<Integer> leftNums = new PriorityQueue<>(cap, (a, b) -> {
            if (nums[b] != nums[a]) {
                return nums[b] > nums[a] ? 1 : -1;
            } else {
                return a - b;
            }
        });
        PriorityQueue<Integer> rightNums = new PriorityQueue<>(cap, (a, b) -> {
            if (nums[a] != nums[b]) {
                return nums[a] > nums[b] ? 1 : -1;
            } else {
                return a - b;
            }
        });
        int leftCount = 0, rightCount = 0;
        for (int i = 0; i < k - 1; i++) {
            if (rightNums.isEmpty() || nums[i] < nums[rightNums.peek()]) {
                leftNums.add(i);
                leftCount++;
            } else {
                rightNums.add(i);
                rightCount++;
            }
        }
        double[] ans = new double[n - k + 1];
        for (int i = k - 1; i < n; i++) {
            // add nums[i]
            Integer rightBound = getValidTop(rightNums, i - k + 1);
            if (rightBound == null || nums[i] < nums[rightBound]) {
                leftNums.add(i);
                leftCount++;
            } else {
                rightNums.add(i);
                rightCount++;
            }
            while (leftCount > rightCount + 1) {
                Integer top = getValidTop(leftNums, i - k + 1);
                leftNums.poll();
                rightNums.add(top);
                leftCount--;
                rightCount++;
            }
            while (leftCount + 1 < rightCount) {
                Integer top = getValidTop(rightNums, i - k + 1);
                rightNums.poll();
                leftNums.add(top);
                leftCount++;
                rightCount--;
            }
            Integer l = getValidTop(leftNums, i - k + 1);
            Integer r = getValidTop(rightNums, i - k + 1);
            if (leftCount == rightCount) {
                ans[i - k + 1] = ((long) nums[l] + nums[r]) / 2D;
            } else if (leftCount > rightCount) {
                ans[i - k + 1] = nums[l];
            } else {
                ans[i - k + 1] = nums[r];
            }
            // rm nums[i - k + 1]
            if (r != null && (i - k + 1 == r || nums[i - k + 1] > nums[r])) {
                rightCount--;
            } else if (i - k + 1 == l || nums[i - k + 1] < nums[l]) {
                leftCount--;
            }
        }
        return ans;
    }

    private Integer getValidTop(PriorityQueue<Integer> queue, int bound) {
        while (!queue.isEmpty() && queue.peek() < bound) {
            queue.poll();
        }
        return queue.peek();
    }


    public static void main(String[] args) {
        int[] nums = {-2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648};
        int k = 2;
        ArrayUtil.print(new No480().medianSlidingWindow(nums, k));

//        System.out.println(-2147483648 < 2147483647);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

