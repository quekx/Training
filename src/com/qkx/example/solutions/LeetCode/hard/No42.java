package com.qkx.example.solutions.LeetCode.hard;

import java.util.Stack;

/**
 * @author kaixin
 * @since 2020-12-01 19:44
 */
//Given n non-negative integers representing an elevation map where the width of
// each bar is 1, compute how much water it can trap after raining.
//
//
// Example 1:
//
//
//Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
//Output: 6
//Explanation: The above elevation map (black section) is represented by array [
//0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are
// being trapped.
//
//
// Example 2:
//
//
//Input: height = [4,2,0,3,2,5]
//Output: 9
//
//
//
// Constraints:
//
//
// n == height.length
// 0 <= n <= 3 * 104
// 0 <= height[i] <= 105
//
// Related Topics Array Two Pointers Stack
// 👍 9009 👎 136


//leetcode submit region begin(Prohibit modification and deletion)
public class No42 {

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(new No42().trap(height));
//        int[] height2 = {2, 0, 2};
//        System.out.println(new No42().trap(height2));
    }

    /**
     * 1. 从左到右遍历
     * 维护一个递增栈，站内元素为下标
     * 下标代表的元素高向右递增
     * 这个栈代表最高点左侧的顶点下标
     *
     * 2. 从右到左遍历
     * 维护一个递增栈，站内元素为下标
     * 下标代表的元素高向左递增
     * 这个栈代表最高点右侧的顶点下标
     *
     * 栈结果即为各个水坑区间的顶点
     * 遍历各个区间计算
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height.length <= 2) {
            return 0;
        }

        Stack<Integer> leftStack = new Stack<>();
        leftStack.push(0);
        for (int i = 1; i <= height.length - 1; i++) {
            if (height[i] >= height[leftStack.peek()]) {
                leftStack.push(i);
            }
        }

        Stack<Integer> rightStack = new Stack<>();
        rightStack.push(height.length - 1);
        for (int i = height.length - 2; i >= 0; i--) {
            if (height[i] > height[rightStack.peek()]) {
                rightStack.push(i);
            }
        }

        int result = 0;
        int left;
        int right;

        right = leftStack.pop();
        while (!leftStack.isEmpty()) {
            left = leftStack.pop();
            result += calculate(height, left, right);
            right = left;
        }

        left = rightStack.pop();
        while (!rightStack.isEmpty()) {
            right = rightStack.pop();
            result += calculate(height, left, right);
            left = right;
        }
        return result;
    }

    private int calculate(int[] height, int left, int right) {
        if (left + 1 >= right) {
            return 0;
        }

        int actualHeight = Math.min(height[left], height[right]);
        int batchResult = 0;
        for (int i = left + 1; i <= right - 1; i++) {
            batchResult += actualHeight - height[i];
        }
        return batchResult;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

