package com.qkx.example.solutions.LeetCode.medium;

/**
 * @author kaixin
 * @since 2020-12-16 11:05
 */
//Given a string s and an integer k, return the length of the longest substring
//of s such that the frequency of each character in this substring is greater than
// or equal to k.
//
//
// Example 1:
//
//
//Input: s = "aaabb", k = 3
//Output: 3
//Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
//
//
// Example 2:
//
//
//Input: s = "ababbc", k = 2
//Output: 5
//Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and
//'b' is repeated 3 times.
//
//
//
// Constraints:
//
//
// 1 <= s.length <= 104
// s consists of only lowercase English letters.
// 1 <= k <= 105
//
// Related Topics Divide and Conquer Recursion Sliding Window
// 👍 2056 👎 247


//leetcode submit region begin(Prohibit modification and deletion)
public class No395 {

    public static void main(String[] args) {
        String s = "bbaaacbd";
        int k = 3;
        System.out.println(new No395().longestSubstring(s, k));
    }

    /**
     *
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:38.7 MB,击败了54.23% 的Java用户
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        char[] arr = s.toCharArray();
        return find(0, s.length() - 1, arr, k);
    }

    /**
     * 分治
     *
     * @return
     */
    private int find(int start, int end, char[] arr, int k) {
        int[] freq = new int[26];
        for (int i = start; i <= end; i++) {
            freq[arr[i] - 'a']++;
        }
        if (isValid(freq, k)) {
            return end - start + 1;
        }

        int result = 0;
        int j = 0;
        int i = 0;
        while (i <= end) {
            if (freq[arr[i] - 'a'] < k) {
                // 这里分成小段
                result = Math.max(result, find(j, i - 1, arr, k));
                j = i + 1;
            }
            i++;
        }
        result = Math.max(result, find(j, i - 1, arr, k));
        return result;
    }

    private boolean isValid(int[] freq, int k) {
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0 && freq[i] < k) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

