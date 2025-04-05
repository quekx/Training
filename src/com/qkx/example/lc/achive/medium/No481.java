package com.qkx.example.lc.achive.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author kaixin
 * @since 2022-02-21 17:33
 */
//A magical string s consists of only '1' and '2' and obeys the following rules:
//
//
//
// The string s is magical because concatenating the number of contiguous
//occurrences of characters '1' and '2' generates the string s itself.
//
// The first few elements of s is s = "1221121221221121122……". If we group the
//consecutive 1's and 2's in s, it will be "1 22 11 2 1 22 1 22 11 2 11 22 ......"
//and the occurrences of 1's or 2's in each group are "1 2 2 1 1 2 1 2 2 1 2 2 ...
//...". You can see that the occurrence sequence is s itself.
//
// Given an integer n, return the number of 1's in the first n number in the
//magical string s.
//
//
// Example 1:
//
//
//Input: n = 6
//Output: 3
//Explanation: The first 6 elements of magical string s is "122112" and it
//contains three 1's, so return 3.
//
//
// Example 2:
//
//
//Input: n = 1
//Output: 1
//
//
//
// Constraints:
//
//
// 1 <= n <= 10⁵
//
// Related Topics Two Pointers String 👍 168 👎 902


//leetcode submit region begin(Prohibit modification and deletion)
class No481 {

    public static void main(String[] args) {
        System.out.println(new No481().magicalString(6));
    }

    /**
     * 解答成功:
     * 执行耗时:9 ms,击败了59.70% 的Java用户
     * 内存消耗:42.3 MB,击败了44.03% 的Java用户
     * @param n
     * @return
     */
    public int magicalString(int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(2);
        int cur = 2;
        int length = 3;
        int result = 1;
        while (length < n && !queue.isEmpty()) {
            int x = queue.poll();
            // 补x个与cur相反的数
            for (int i = 1; i <= x; i++) {
                queue.add(cur == 1 ? 2 : 1);
            }
            if (cur == 2) {
                if (length + x >= n) {
                    result += n - length;
                } else {
                    result += x;
                }
            }
            length += x;
            cur = cur == 1 ? 2 : 1;
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
