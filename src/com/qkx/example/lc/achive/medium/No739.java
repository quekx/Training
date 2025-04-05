package com.qkx.example.lc.achive.medium;

//Given an array of integers temperatures represents the daily temperatures,
//return an array answer such that answer[i] is the number of days you have to wait
//after the iᵗʰ day to get a warmer temperature. If there is no future day for
//which this is possible, keep answer[i] == 0 instead.
//
//
// Example 1:
// Input: temperatures = [73,74,75,71,69,72,76,73]
//Output: [1,1,4,2,1,1,0,0]
//
// Example 2:
// Input: temperatures = [30,40,50,60]
//Output: [1,1,1,0]
//
// Example 3:
// Input: temperatures = [30,60,90]
//Output: [1,1,0]
//
//
// Constraints:
//
//
// 1 <= temperatures.length <= 10⁵
// 30 <= temperatures[i] <= 100
//
//
// Related Topics Array Stack Monotonic Stack 👍 8824 👎 202


import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class No739 {

    /**
     * 官方答案，使用 LinkedList 作为栈更快
     *
     * 解答成功:
     * 	执行耗时:50 ms,击败了83.11% 的Java用户
     * 	内存消耗:129.9 MB,击败了73.58% 的Java用户
     */
    public int[] dailyTemperatures3(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < length; i++) {
            int temperature = temperatures[i];
            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * 解答成功:
     * 执行耗时:190 ms,击败了42.48% 的Java用户
     * 内存消耗:138.7 MB,击败了6.63% 的Java用户
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!s.isEmpty() && temperatures[s.peek()] < temperatures[i]) {
                int pre = s.pop();
                ans[pre] = i - pre;
            }
            s.push(i);
        }
        return ans;
    }

    /**
     * 解答成功:
     * 执行耗时:226 ms,击败了22.07% 的Java用户
     * 内存消耗:132 MB,击败了70.54% 的Java用户
     */
    public int[] dailyTemperatures2(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Stack<Integer> s = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            int cur = temperatures[i];
            while (!s.isEmpty() && temperatures[s.peek()] <= cur) {
                s.pop();
            }
            ans[i] = s.isEmpty() ? 0 : s.peek() - i;
            s.push(i);
        }
        return ans;
    }

    /**
     * 替换 LinkedList 作为栈
     *
     * 解答成功:
     * 执行耗时:75 ms,击败了74.52% 的Java用户
     * 内存消耗:134.2 MB,击败了53.28% 的Java用户
     */
    public int[] dailyTemperatures4(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Deque<Integer> s = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--) {
            int cur = temperatures[i];
            while (!s.isEmpty() && temperatures[s.peek()] <= cur) {
                s.pop();
            }
            ans[i] = s.isEmpty() ? 0 : s.peek() - i;
            s.push(i);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

