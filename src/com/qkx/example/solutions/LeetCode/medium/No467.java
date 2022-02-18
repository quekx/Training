package com.qkx.example.solutions.LeetCode.medium;

/**
 * @author kaixin
 * @since 2022-02-18 15:04
 */
//We define the string s to be the infinite wraparound string of
//"abcdefghijklmnopqrstuvwxyz", so s will look like this:
//
//
// "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
//
//
// Given a string p, return the number of unique non-empty substrings of p are
//present in s.
//
//
// Example 1:
//
//
//Input: p = "a"
//Output: 1
//Explanation: Only the substring "a" of p is in s.
//
//
// Example 2:
//
//
//Input: p = "cac"
//Output: 2
//Explanation: There are two substrings ("a", "c") of p in s.
//
//
// Example 3:
//
//
//Input: p = "zab"
//Output: 6
//Explanation: There are six substrings ("z", "a", "b", "za", "ab", and "zab")
//of p in s.
//
//
//
// Constraints:
//
//
// 1 <= p.length <= 10⁵
// p consists of lowercase English letters.
//
// Related Topics String Dynamic Programming 👍 1018 👎 129


//leetcode submit region begin(Prohibit modification and deletion)
class No467 {
    /**
     * dp[i] 代表范围：s(0,i)中, 以 si 为结尾的连续的子串的数目
     * dp[0] = 1
     * dp[i] = si-1和si是否连续？dp[i-1]+1 : 1
     *
     * @param p
     * @return
     */
    public int findSubstringInWraproundString(String p) {
        if (p == null || p.length() == 0) {
            return 0;
        }

        int dp[] = new int[p.length()];
        int nums[] = new int[26];
        dp[0] = 1;
        nums[p.charAt(0) - 'a'] = 1;
        for (int i = 1; i <= p.length() - 1; i++) {
            if (isAdjust(p.charAt(i - 1), p.charAt(i))) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
            int target = p.charAt(i) - 'a';
            nums[target] = Math.max(nums[target], dp[i]);
        }

        int result = 0;
        for (int num : nums) {
            result += num;
        }
        return result;
    }

    private boolean isAdjust(char pre, char cur) {
        int delta = cur - pre;
        return delta == 1 || delta == -25;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
